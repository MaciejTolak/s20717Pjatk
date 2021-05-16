using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Models;

namespace WebApplication1.DTOs
{
    public class PrescriptionDTO
    {
        public int IdPrescription { get; set; }
        public DateTime Date { get; set; }
        public DateTime DueDate { get; set; }
        public string FirstNameDoctor { get; set; }
        public string LastNameDoctor { get; set; }
        public string  FirstNamePatient { get; set; }
        public string LastNamePatient { get; set; }
        public DateTime BirthDate { get; set; }
        public ICollection<MedicamentDTO> Medicaments { get; set; }

    }
}
