using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class Prescription
    {
        public Prescription()
        {
            Prescription_Medicaments = new HashSet<Prescription_Medicament>();
        }
        [Key]
        public int IdPrescription { get; set; }
        [Required]
        public DateTime Date { get; set; }
        [Required]
        public DateTime DueDate { get; set; }

        public int IdDoctor { get; set; }
        [ForeignKey(nameof(IdDoctor))]
        public virtual Doctor IdDoctorNavigation { get; set; }

        public int IdPatient { get; set; }
        [ForeignKey(nameof(IdPatient))]
        public virtual Patient IdPatientNavigation { get; set; }


        public virtual ICollection<Prescription_Medicament> Prescription_Medicaments { get; set; }
    }
}
