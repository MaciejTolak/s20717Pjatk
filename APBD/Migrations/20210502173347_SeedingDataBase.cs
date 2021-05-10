using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace WebApplication1.Migrations
{
    public partial class SeedingDataBase : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Prescription_Medicaments_Medicaments_IdMedicamnet",
                table: "Prescription_Medicaments");

            migrationBuilder.RenameColumn(
                name: "Detalis",
                table: "Prescription_Medicaments",
                newName: "Details");

            migrationBuilder.RenameColumn(
                name: "IdMedicamnet",
                table: "Prescription_Medicaments",
                newName: "IdMedicament");

            migrationBuilder.InsertData(
                table: "Doctors",
                columns: new[] { "IdDoctor", "Email", "FirstName", "LastName" },
                values: new object[,]
                {
                    { 1, "cos@cos.pl", "Mateusz", "Kownacki" },
                    { 2, "cos123@dsa.pl", "Jakub", "Romanowska" },
                    { 3, "ktos123@cdass.pl", "Karolina", "Kownacka" }
                });

            migrationBuilder.InsertData(
                table: "Medicaments",
                columns: new[] { "IdMedicament", "Description", "Name", "Type" },
                values: new object[,]
                {
                    { 1, "Tabletka musująca na ból i gorączke", "APAP", "AAA" },
                    { 2, "Lek pomocniczy w leczeniu nadpobudliwości psychoruchowej", "Aderall", "BBB" },
                    { 3, "Tabletka powlekana Ibuprom zawiera 200 mg ibuprofenu.", "Ibuprom", "CCC" }
                });

            migrationBuilder.InsertData(
                table: "Patients",
                columns: new[] { "IdPatient", "BirthDate", "FirstName", "LastName" },
                values: new object[,]
                {
                    { 1, new DateTime(2000, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified), "Mateusz", "Utrata" },
                    { 2, new DateTime(1999, 5, 26, 5, 10, 20, 0, DateTimeKind.Unspecified), "Artur", "Tilokani" },
                    { 3, new DateTime(2001, 11, 6, 5, 10, 20, 0, DateTimeKind.Unspecified), "Joanna", "Wrześniak" }
                });

            migrationBuilder.InsertData(
                table: "Prescriptions",
                columns: new[] { "IdPrescription", "Date", "DueDate", "IdDoctor", "IdPatient" },
                values: new object[] { 1, new DateTime(2019, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified), new DateTime(2020, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified), 1, 1 });

            migrationBuilder.InsertData(
                table: "Prescriptions",
                columns: new[] { "IdPrescription", "Date", "DueDate", "IdDoctor", "IdPatient" },
                values: new object[] { 2, new DateTime(2015, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified), new DateTime(2016, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified), 2, 2 });

            migrationBuilder.InsertData(
                table: "Prescriptions",
                columns: new[] { "IdPrescription", "Date", "DueDate", "IdDoctor", "IdPatient" },
                values: new object[] { 3, new DateTime(2020, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified), new DateTime(2001, 12, 31, 5, 10, 20, 0, DateTimeKind.Unspecified), 3, 3 });

            migrationBuilder.InsertData(
                table: "Prescription_Medicaments",
                columns: new[] { "IdMedicament", "IdPrescription", "Details", "Dose" },
                values: new object[] { 1, 1, "Dziennie", 20 });

            migrationBuilder.InsertData(
                table: "Prescription_Medicaments",
                columns: new[] { "IdMedicament", "IdPrescription", "Details", "Dose" },
                values: new object[] { 2, 2, "Dziennie", 3 });

            migrationBuilder.InsertData(
                table: "Prescription_Medicaments",
                columns: new[] { "IdMedicament", "IdPrescription", "Details", "Dose" },
                values: new object[] { 3, 3, "Dziennie", 4 });

            migrationBuilder.AddForeignKey(
                name: "FK_Prescription_Medicaments_Medicaments_IdMedicament",
                table: "Prescription_Medicaments",
                column: "IdMedicament",
                principalTable: "Medicaments",
                principalColumn: "IdMedicament",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Prescription_Medicaments_Medicaments_IdMedicament",
                table: "Prescription_Medicaments");

            migrationBuilder.DeleteData(
                table: "Prescription_Medicaments",
                keyColumns: new[] { "IdMedicament", "IdPrescription" },
                keyValues: new object[] { 1, 1 });

            migrationBuilder.DeleteData(
                table: "Prescription_Medicaments",
                keyColumns: new[] { "IdMedicament", "IdPrescription" },
                keyValues: new object[] { 2, 2 });

            migrationBuilder.DeleteData(
                table: "Prescription_Medicaments",
                keyColumns: new[] { "IdMedicament", "IdPrescription" },
                keyValues: new object[] { 3, 3 });

            migrationBuilder.DeleteData(
                table: "Medicaments",
                keyColumn: "IdMedicament",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Medicaments",
                keyColumn: "IdMedicament",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Medicaments",
                keyColumn: "IdMedicament",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Prescriptions",
                keyColumn: "IdPrescription",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Prescriptions",
                keyColumn: "IdPrescription",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Prescriptions",
                keyColumn: "IdPrescription",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Doctors",
                keyColumn: "IdDoctor",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Doctors",
                keyColumn: "IdDoctor",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Doctors",
                keyColumn: "IdDoctor",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Patients",
                keyColumn: "IdPatient",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Patients",
                keyColumn: "IdPatient",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Patients",
                keyColumn: "IdPatient",
                keyValue: 3);

            migrationBuilder.RenameColumn(
                name: "Details",
                table: "Prescription_Medicaments",
                newName: "Detalis");

            migrationBuilder.RenameColumn(
                name: "IdMedicament",
                table: "Prescription_Medicaments",
                newName: "IdMedicamnet");

            migrationBuilder.AddForeignKey(
                name: "FK_Prescription_Medicaments_Medicaments_IdMedicamnet",
                table: "Prescription_Medicaments",
                column: "IdMedicamnet",
                principalTable: "Medicaments",
                principalColumn: "IdMedicament",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
