﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using WebApplication1.Models;

namespace WebApplication1.Migrations
{
    [DbContext(typeof(s20717Context))]
    [Migration("20210509160504_AddedRefreshTokenToUser")]
    partial class AddedRefreshTokenToUser
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("Relational:MaxIdentifierLength", 128)
                .HasAnnotation("ProductVersion", "5.0.5")
                .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

            modelBuilder.Entity("WebApplication1.Models.Doctor", b =>
                {
                    b.Property<int>("IdDoctor")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Email")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.Property<string>("FirstName")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.Property<string>("LastName")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.HasKey("IdDoctor");

                    b.ToTable("Doctors");

                    b.HasData(
                        new
                        {
                            IdDoctor = 1,
                            Email = "cos@cos.pl",
                            FirstName = "Mateusz",
                            LastName = "Kownacki"
                        },
                        new
                        {
                            IdDoctor = 2,
                            Email = "cos123@dsa.pl",
                            FirstName = "Jakub",
                            LastName = "Romanowska"
                        },
                        new
                        {
                            IdDoctor = 3,
                            Email = "ktos123@cdass.pl",
                            FirstName = "Karolina",
                            LastName = "Kownacka"
                        });
                });

            modelBuilder.Entity("WebApplication1.Models.Medicament", b =>
                {
                    b.Property<int>("IdMedicament")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Description")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.Property<string>("Type")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.HasKey("IdMedicament");

                    b.ToTable("Medicaments");

                    b.HasData(
                        new
                        {
                            IdMedicament = 1,
                            Description = "Tabletka musująca na ból i gorączke",
                            Name = "APAP",
                            Type = "AAA"
                        },
                        new
                        {
                            IdMedicament = 2,
                            Description = "Lek pomocniczy w leczeniu nadpobudliwości psychoruchowej",
                            Name = "Aderall",
                            Type = "BBB"
                        },
                        new
                        {
                            IdMedicament = 3,
                            Description = "Tabletka powlekana Ibuprom zawiera 200 mg ibuprofenu.",
                            Name = "Ibuprom",
                            Type = "CCC"
                        });
                });

            modelBuilder.Entity("WebApplication1.Models.Patient", b =>
                {
                    b.Property<int>("IdPatient")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<DateTime>("BirthDate")
                        .HasColumnType("datetime2");

                    b.Property<string>("FirstName")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.Property<string>("LastName")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.HasKey("IdPatient");

                    b.ToTable("Patients");

                    b.HasData(
                        new
                        {
                            IdPatient = 1,
                            BirthDate = new DateTime(2000, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified),
                            FirstName = "Mateusz",
                            LastName = "Utrata"
                        },
                        new
                        {
                            IdPatient = 2,
                            BirthDate = new DateTime(1999, 5, 26, 5, 10, 20, 0, DateTimeKind.Unspecified),
                            FirstName = "Artur",
                            LastName = "Tilokani"
                        },
                        new
                        {
                            IdPatient = 3,
                            BirthDate = new DateTime(2001, 11, 6, 5, 10, 20, 0, DateTimeKind.Unspecified),
                            FirstName = "Joanna",
                            LastName = "Wrześniak"
                        });
                });

            modelBuilder.Entity("WebApplication1.Models.Prescription", b =>
                {
                    b.Property<int>("IdPrescription")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<DateTime>("Date")
                        .HasColumnType("datetime2");

                    b.Property<DateTime>("DueDate")
                        .HasColumnType("datetime2");

                    b.Property<int>("IdDoctor")
                        .HasColumnType("int");

                    b.Property<int>("IdPatient")
                        .HasColumnType("int");

                    b.HasKey("IdPrescription");

                    b.HasIndex("IdDoctor");

                    b.HasIndex("IdPatient");

                    b.ToTable("Prescriptions");

                    b.HasData(
                        new
                        {
                            IdPrescription = 1,
                            Date = new DateTime(2019, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified),
                            DueDate = new DateTime(2020, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified),
                            IdDoctor = 1,
                            IdPatient = 1
                        },
                        new
                        {
                            IdPrescription = 2,
                            Date = new DateTime(2015, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified),
                            DueDate = new DateTime(2016, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified),
                            IdDoctor = 2,
                            IdPatient = 2
                        },
                        new
                        {
                            IdPrescription = 3,
                            Date = new DateTime(2020, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified),
                            DueDate = new DateTime(2001, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified),
                            IdDoctor = 3,
                            IdPatient = 3
                        });
                });

            modelBuilder.Entity("WebApplication1.Models.Prescription_Medicament", b =>
                {
                    b.Property<int>("IdMedicament")
                        .HasColumnType("int");

                    b.Property<int>("IdPrescription")
                        .HasColumnType("int");

                    b.Property<string>("Details")
                        .IsRequired()
                        .HasMaxLength(100)
                        .HasColumnType("nvarchar(100)");

                    b.Property<int?>("Dose")
                        .HasColumnType("int");

                    b.HasKey("IdMedicament", "IdPrescription");

                    b.HasIndex("IdPrescription");

                    b.ToTable("Prescription_Medicaments");

                    b.HasData(
                        new
                        {
                            IdMedicament = 1,
                            IdPrescription = 1,
                            Details = "Dziennie",
                            Dose = 20
                        },
                        new
                        {
                            IdMedicament = 2,
                            IdPrescription = 2,
                            Details = "Dziennie",
                            Dose = 3
                        },
                        new
                        {
                            IdMedicament = 3,
                            IdPrescription = 3,
                            Details = "Dziennie",
                            Dose = 4
                        });
                });

            modelBuilder.Entity("WebApplication1.Models.User", b =>
                {
                    b.Property<int>("IdUser")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int")
                        .HasAnnotation("SqlServer:ValueGenerationStrategy", SqlServerValueGenerationStrategy.IdentityColumn);

                    b.Property<string>("Login")
                        .IsRequired()
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("Password")
                        .IsRequired()
                        .HasColumnType("nvarchar(max)");

                    b.Property<string>("RefreshToken")
                        .HasColumnType("nvarchar(max)");

                    b.Property<DateTime?>("RefreshTokenExp")
                        .HasColumnType("datetime2");

                    b.Property<string>("Salt")
                        .HasColumnType("nvarchar(max)");

                    b.HasKey("IdUser");

                    b.ToTable("Users");
                });

            modelBuilder.Entity("WebApplication1.Models.Prescription", b =>
                {
                    b.HasOne("WebApplication1.Models.Doctor", "IdDoctorNavigation")
                        .WithMany("Prescriptions")
                        .HasForeignKey("IdDoctor")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("WebApplication1.Models.Patient", "IdPatientNavigation")
                        .WithMany("Prescriptions")
                        .HasForeignKey("IdPatient")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("IdDoctorNavigation");

                    b.Navigation("IdPatientNavigation");
                });

            modelBuilder.Entity("WebApplication1.Models.Prescription_Medicament", b =>
                {
                    b.HasOne("WebApplication1.Models.Medicament", "IdMedicamentNavigation")
                        .WithMany("Prescription_Medicaments")
                        .HasForeignKey("IdMedicament")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("WebApplication1.Models.Prescription", "IdPrescriptionNavigation")
                        .WithMany("Prescription_Medicaments")
                        .HasForeignKey("IdPrescription")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("IdMedicamentNavigation");

                    b.Navigation("IdPrescriptionNavigation");
                });

            modelBuilder.Entity("WebApplication1.Models.Doctor", b =>
                {
                    b.Navigation("Prescriptions");
                });

            modelBuilder.Entity("WebApplication1.Models.Medicament", b =>
                {
                    b.Navigation("Prescription_Medicaments");
                });

            modelBuilder.Entity("WebApplication1.Models.Patient", b =>
                {
                    b.Navigation("Prescriptions");
                });

            modelBuilder.Entity("WebApplication1.Models.Prescription", b =>
                {
                    b.Navigation("Prescription_Medicaments");
                });
#pragma warning restore 612, 618
        }
    }
}
