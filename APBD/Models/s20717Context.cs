using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication1.Models
{
    public class s20717Context : DbContext
    {
        public s20717Context()
        {

        }

        public s20717Context(DbContextOptions<s20717Context> options) : base(options)
        {

        }

        public virtual DbSet<Doctor> Doctors { get; set; }
        public virtual DbSet<Medicament> Medicaments { get; set; }
        public virtual DbSet<Patient> Patients { get; set; }
        public virtual DbSet<Prescription> Prescriptions { get; set; }
        public virtual DbSet<User> Users { get; set; }
        public virtual DbSet<Prescription_Medicament> Prescription_Medicaments { get; set; }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            base.OnConfiguring(optionsBuilder);
            optionsBuilder
                .UseLazyLoadingProxies()
                .UseSqlServer("Data Source=db-mssql16.pjwstk.edu.pl;Initial Catalog=s20717;Integrated Security=True");
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
            modelBuilder.Entity<Prescription_Medicament>(opt =>
            {
                opt.HasKey(e => new { e.IdMedicament, e.IdPrescription });

                opt.Property(e => e.IdPrescription).IsRequired();
                opt.HasOne(e => e.IdPrescriptionNavigation).WithMany(e => e.Prescription_Medicaments).HasForeignKey(e => e.IdPrescription);

                opt.Property(e => e.IdMedicament).IsRequired();
                opt.HasOne(e => e.IdMedicamentNavigation).WithMany(e => e.Prescription_Medicaments).HasForeignKey(e => e.IdMedicament);

                opt.Property(e => e.Details).IsRequired().HasMaxLength(100);

            });

            modelBuilder.Entity<Doctor>(opt =>
            {
                opt.HasData(
                    new Doctor { IdDoctor = 1, FirstName = "Mateusz", LastName = "Kownacki", Email = "cos@cos.pl" },
                    new Doctor { IdDoctor = 2, FirstName = "Jakub", LastName = "Romanowska", Email = "cos123@dsa.pl" },
                    new Doctor { IdDoctor = 3, FirstName = "Karolina", LastName = "Kownacka", Email = "ktos123@cdass.pl" }
                    );
            });

            modelBuilder.Entity<Patient>(opt =>
            {
                opt.HasData(
                    new Patient { IdPatient = 1, FirstName = "Mateusz", LastName = "Utrata", BirthDate = new DateTime(2000, 12, 31, 5, 10, 20) },
                new Patient { IdPatient = 2, FirstName = "Artur", LastName = "Tilokani", BirthDate = new DateTime(1999, 5, 26, 5, 10, 20) },
                new Patient { IdPatient = 3, FirstName = "Joanna", LastName = "Wrześniak", BirthDate = new DateTime(2001, 11, 6, 5, 10, 20) });
            });

            modelBuilder.Entity<Prescription>(opt =>
            {
                opt.HasData(
                    new Prescription { IdPrescription = 1, Date = new DateTime(2019, 12, 31, 5, 10, 20), DueDate = new DateTime(2020, 12, 31, 5, 10, 20), IdPatient = 1, IdDoctor = 1 },
                new Prescription { IdPrescription = 2, Date = new DateTime(2015, 12, 31, 5, 10, 20), DueDate = new DateTime(2016, 12, 31, 5, 10, 20), IdPatient = 2, IdDoctor = 2 },
                new Prescription { IdPrescription = 3, Date = new DateTime(2020, 12, 31, 5, 10, 20), DueDate = new DateTime(2001, 12, 31, 5, 10, 20), IdPatient = 3, IdDoctor = 3 });
            });

            modelBuilder.Entity<Medicament>(opt =>
            {
                opt.HasData(
                    new Medicament { IdMedicament = 1, Name = "APAP", Description = "Tabletka musująca na ból i gorączke", Type = "AAA" },
                new Medicament { IdMedicament = 2, Name = "Aderall", Description = "Lek pomocniczy w leczeniu nadpobudliwości psychoruchowej", Type = "BBB" },
                new Medicament { IdMedicament = 3, Name = "Ibuprom", Description = "Tabletka powlekana Ibuprom zawiera 200 mg ibuprofenu.", Type = "CCC" });
            });
            
            modelBuilder.Entity<Prescription_Medicament>(opt =>
            {
                opt.HasData(
                new Prescription_Medicament { IdMedicament = 1, IdPrescription = 1, Dose = 20, Details = "Dziennie" },
                new Prescription_Medicament { IdMedicament = 2, IdPrescription = 2, Dose = 3, Details = "Dziennie" },
                new Prescription_Medicament { IdMedicament = 3, IdPrescription = 3, Dose = 4, Details = "Dziennie" });
            });

        }
    }
}
