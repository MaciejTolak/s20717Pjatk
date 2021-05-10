
using Microsoft.AspNetCore.Cryptography.KeyDerivation;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AccountsController : ControllerBase
    {
        private s20717Context context;
        private IConfiguration Configuration;

        public AccountsController(s20717Context context, IConfiguration configuration)
        {
            this.context = context;
            this.Configuration = configuration;
        }

        [HttpPost]
        public IActionResult Login(string login, string password)
        {
            var user = context.Users.Where(e => e.Login == login).FirstOrDefault();
            if (user == null)
            {
                return BadRequest("Nie ma takiego użytkownika");
            }
            string userpassword = user.Password;
            byte[] salt = Convert.FromBase64String(user.Salt);
            string currHashPassword = Convert.ToBase64String(KeyDerivation.Pbkdf2(
                 password: password,
                 salt: salt,
                 prf: KeyDerivationPrf.HMACSHA1,
                 iterationCount: 10000,
                 numBytesRequested: 256 / 8));
            if (userpassword != currHashPassword)
            {
                return Unauthorized("Podano złe hasło!!!");
            }
            SymmetricSecurityKey symmetricSecurityKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(Configuration["SecretKey"]));
            SigningCredentials signingCredentials = new SigningCredentials(symmetricSecurityKey, SecurityAlgorithms.HmacSha256);
            Claim[] claims = new[] { new Claim(ClaimTypes.Name, "Maciej"), new Claim(ClaimTypes.Role, "Admin"), new Claim(ClaimTypes.Role, "User") };
            JwtSecurityToken jwtSecurityToken = new JwtSecurityToken(
                issuer: "https://localhost:44338",
                audience: "https://localhost:44338",
                claims: claims,
                expires: DateTime.Now.AddMinutes(20),
                signingCredentials: signingCredentials
            );
            byte[] RandomNumber = new byte[32];
            using (var r = RandomNumberGenerator.Create())
            {
                r.GetBytes(RandomNumber);
            }

            string RefreshToken = Convert.ToBase64String(RandomNumber);
            user.RefreshToken = RefreshToken;
            user.RefreshTokenExp = DateTime.Now.AddMinutes(20);
            context.SaveChanges();
            return Ok(new { Token = new JwtSecurityTokenHandler().WriteToken(jwtSecurityToken), RefreshToken =  RefreshToken});
        }
        [HttpPost("newUser")]
        public IActionResult AddUser(string login, string password)
        {
            var user = context.Users.Where(e => e.Login == login).FirstOrDefault();
            if (user != null)
            {
                return BadRequest("Nie ma takiego uzytkownika");
            }
            byte[] salt = new byte[128 / 8];

            using (var rng = RandomNumberGenerator.Create())
            {
                rng.GetBytes(salt);
            }
            string saltString = Convert.ToBase64String(salt);

            string currentHashedPassword = Convert.ToBase64String(KeyDerivation.Pbkdf2(
            password: password,
            salt: Convert.FromBase64String(saltString),
            prf: KeyDerivationPrf.HMACSHA1,
            iterationCount: 10000,
            numBytesRequested: 256 / 8));


            var newUser = new User
            {
               
                Login = login,
                Password = currentHashedPassword,
                Salt = saltString
            };
            context.Users.Add(newUser);
            context.SaveChanges();

            return Ok("Dodano nowego użytkownika");
        }

        [HttpPost("RefreshToken")]
        public IActionResult CreateAccesTokenFromRefreshToken(string refresh)
        {
            var user =context.Users.Where(e => e.RefreshToken == refresh).FirstOrDefault();
            if(user == null)
            {
                return BadRequest("Nie ma takiego uzytkownika");
            }

            if(DateTime.Now > user.RefreshTokenExp)
            {
                return BadRequest("Czas minał!!!");
            }
              SymmetricSecurityKey symmetricSecurityKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(Configuration["SecretKey"]));
            SigningCredentials signingCredentials = new SigningCredentials(symmetricSecurityKey, SecurityAlgorithms.HmacSha256);
            Claim[] claims = new[] { new Claim(ClaimTypes.Name, "Maciej"), new Claim(ClaimTypes.Role, "Admin"), new Claim(ClaimTypes.Role, "User") };
            var AccToken = new JwtSecurityToken(
                issuer: "https://localhost:44338",
                audience: "https://localhost:44338",
                claims: claims,
                expires: DateTime.Now.AddMinutes(20),
                signingCredentials: signingCredentials
            );
            byte[] RandomNumber = new byte[32];
            using (var r = RandomNumberGenerator.Create())
            {
                r.GetBytes(RandomNumber);
            }

            string RefreshToken = Convert.ToBase64String(RandomNumber);
            user.RefreshToken = RefreshToken;
            user.RefreshTokenExp = DateTime.Now.AddMinutes(20);
            context.SaveChanges();
            return Ok(new { Token = new JwtSecurityTokenHandler().WriteToken(AccToken), RefreshToken = RefreshToken });
        }

    }
}
