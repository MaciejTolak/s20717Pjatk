using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class Prescription_Medicament
    {
        public int? Dose { get; set; }
        public string Details { get; set; }
        public int IdMedicament { get; set; }
        public virtual Medicament IdMedicamentNavigation { get; set; }
        public int IdPrescription { get; set; }
        public virtual Prescription IdPrescriptionNavigation { get; set; }
    }
}
