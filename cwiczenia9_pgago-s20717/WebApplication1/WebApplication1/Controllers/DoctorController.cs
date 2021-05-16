using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.DTOs;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    [Authorize]
    [Route("api/[controller]")]
    [ApiController]
    public class DoctorController : ControllerBase
    {

        s20717Context context;
        public DoctorController(s20717Context context)
        {
            this.context = context;
        }

       
        [HttpGet]
        public IActionResult GetDoctors()
        {

            IEnumerable<DoctorDTO> list = context.Doctors.Select(e => new DoctorDTO {IdDoctor = e.IdDoctor,  FirstName = e.FirstName, LastName = e.LastName, Email = e.Email });

            return Ok(list);
        }

        [HttpDelete]
        public IActionResult DeleteDoctor(int IdDoctor)
        {
            if(!context.Doctors.Where(e => e.IdDoctor == IdDoctor).Any())
            {
                return BadRequest("Nie ma takiego doktora");
            }
            Doctor doc = context.Doctors.First(e => e.IdDoctor == IdDoctor);
            context.Doctors.Remove(doc);
            context.SaveChanges();

            return Accepted("Usunieto doktora");
        }
        [HttpPost]
        public IActionResult AddNewDoctor(DoctorDTO doctor)
        {
            if (context.Doctors.Where(e => e.IdDoctor == doctor.IdDoctor).Any())
            {
                return BadRequest("Doktor o takim Id już istnieje");
            }
            Doctor d = new Doctor();
            d.FirstName = doctor.FirstName;
            d.LastName = doctor.LastName;
            d.Email = doctor.Email;
            context.Doctors.Add(d);
            context.SaveChanges();

            return StatusCode(201, "Utowrzono nowego doktora");

        }

        [HttpPut]
        public IActionResult EditDoctor(DoctorDTO doctor)
        {
            if (!context.Doctors.Where(e => e.IdDoctor == doctor.IdDoctor).Any())
            {
                return BadRequest("Nie ma takiego doktora");
            }
            Doctor OldDoctor = context.Doctors.Where(e => e.IdDoctor == doctor.IdDoctor).First();

            OldDoctor.FirstName = doctor.FirstName;
            OldDoctor.LastName = doctor.LastName;
            OldDoctor.Email = doctor.Email;

            context.SaveChanges();
               

            return StatusCode(204, "Zedytowano pomyślnie Doktora");
        }
    }
}
