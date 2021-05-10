using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
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
    public class PrescriptionController : ControllerBase
    {
        s20717Context context;

        public PrescriptionController(s20717Context context)
        {
            this.context = context;
        }

        [HttpGet]
        public IActionResult GetPrescription(int IdPrescription)
        {
            if(!context.Prescriptions.Where(e => e.IdPrescription == IdPrescription).Any())
            {
                return BadRequest("Nie ma takiej recepty");
            }

            PrescriptionDTO prescription = context.Prescriptions
                  .Include(e => e.IdPatientNavigation)
                  .Include(e => e.IdDoctorNavigation)
                  .Include(e => e.Prescription_Medicaments)
                  .ThenInclude(e => e.IdMedicamentNavigation)
                  .Select(e => new PrescriptionDTO
                  {
                      IdPrescription = e.IdPrescription,
                      Date = e.Date,
                      DueDate = e.DueDate,
                      FirstNameDoctor = e.IdDoctorNavigation.FirstName,
                      LastNameDoctor = e.IdDoctorNavigation.LastName,
                      FirstNamePatient = e.IdPatientNavigation.FirstName,
                      LastNamePatient = e.IdPatientNavigation.LastName,
                      BirthDate = e.IdPatientNavigation.BirthDate,
                      Medicaments = e.Prescription_Medicaments.Select(e => new MedicamentDTO
                      {
                          Name = e.IdMedicamentNavigation.Name,
                          Description = e.IdMedicamentNavigation.Description,
                          Type = e.IdMedicamentNavigation.Type
                      }).ToList()
                  }).First(e => e.IdPrescription == IdPrescription);

            return Ok(prescription);
        }

    }
}
