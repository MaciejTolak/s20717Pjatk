using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class Patient
    {
        public Patient()
        {
            Prescriptions = new HashSet<Prescription>();
        }
        [Key]
        public int IdPatient { get; set; }
        [Required]
        [MaxLength(100)]
        public string  FirstName { get; set; }
        [Required]
        [MaxLength(100)]
        public string LastName { get; set; }
        [Required]
        public DateTime BirthDate { get; set; }
        public virtual ICollection<Prescription> Prescriptions { get; set; }

    }
}
