//
// Created by maciej on 24.06.2020.
//
#include <iostream>
#include <string>
#include "Cezar.h"

using namespace std;


Cezar::Cezar(const string &login, const string &haslo) {
    daneUzytkownika.login = login;
    daneUzytkownika.haslo = haslo;
    this->przesuniecie = 2;
}

void Cezar::ustawPrzesuniecie(int przesuniecie) {
    this->przesuniecie = przesuniecie;
}

void Cezar::dodajHaslo(string haslo) {
    listaHasel.push_back(haslo);
}

void Cezar::usunHaslo(string haslousun) {

    listaHasel.erase(remove(listaHasel.begin(), listaHasel.end(), haslousun), listaHasel.end());
}

void Cezar::wyswietlListeHasel() {
    for (string haslo : listaHasel) {
        cout << "haslo: " << haslo << endl;
    }
}

string Cezar::szyfrowanie(string haslo) {

    for (int i = 0; i <= haslo.length(); i++) {
        if (haslo[i] >= 65 && haslo[i] <= 90 - przesuniecie)
            haslo[i] = int(haslo[i]) + przesuniecie;
        else if (haslo[i] >= 91 - przesuniecie && haslo[i] <= 90)
            haslo[i] = int(haslo[i]) - 26 + przesuniecie;
        else if (haslo[i] >= 97 && haslo[i] <= 122 - przesuniecie)
            haslo[i] = int(haslo[i]) + przesuniecie;
        else if (haslo[i] >= 123 - przesuniecie && haslo[i] <= 122)
            haslo[i] = int(haslo[i]) - 26 + przesuniecie;

    }
    return haslo;
}

string Cezar::odszyfrowanie(string haslo) {

    int przesuniecieTMP = przesuniecie * (-1);

    for (int i = 0; i <= haslo.length(); i++) {
        if (haslo[i] >= 65 && haslo[i] <= 90 - przesuniecieTMP)
            haslo[i] = int(haslo[i]) + przesuniecieTMP;
        else if (haslo[i] >= 91 - przesuniecieTMP && haslo[i] <= 90)
            haslo[i] = int(haslo[i]) - 26 + przesuniecieTMP;
        else if (haslo[i] >= 97 && haslo[i] <= 122 - przesuniecieTMP)
            haslo[i] = int(haslo[i]) + przesuniecieTMP;
        else if (haslo[i] >= 123 - przesuniecieTMP && haslo[i] <= 122)
            haslo[i] = int(haslo[i]) - 26 + przesuniecieTMP;

    }
    return haslo;
}

void Cezar::zapiszDoPilku() {
    fstream plik1;
    plik1.open("hasla.txt");
    for (string haslo : listaHasel) {
        plik1 << szyfrowanie(haslo) << endl;
    }
    plik1.close();

}

void Cezar::odczytajZPliku() {
    ifstream plik;
    string wiersz;
    plik.open("hasla.txt");
    if (plik.good()) {
        while (getline(plik, wiersz)) {
            cout << odszyfrowanie(wiersz) << endl;
        }
    } else {
        cout << "Nie ma takiego pliku!!";
    }
}

void Cezar::wczytanieZPliku() {
    ifstream plik;
    string wiersz;
    plik.open("hasla.txt");
    if (plik.good()) {
        while (getline(plik, wiersz)) {
            listaHasel.push_back(odszyfrowanie(wiersz));
        }
    }
}

void Cezar::wyswietlDaneUzytkownika() {
    cout<<daneUzytkownika.login<<" "<<daneUzytkownika.haslo<<endl;
}


