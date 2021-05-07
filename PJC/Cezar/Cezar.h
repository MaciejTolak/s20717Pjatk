//
// Created by maciej on 24.06.2020.
//

#ifndef PROJEKT_S20717_CEZAR_H
#define PROJEKT_S20717_CEZAR_H


using namespace std;

#include <fstream>
#include <string>
#include <vector>
#include <algorithm>

struct DaneUzytkownika {
    string login;
    string haslo;
};


class Cezar {
    int przesuniecie;
    vector<string> listaHasel;
    DaneUzytkownika daneUzytkownika;

public:
    Cezar(const string &login, const string &haslo);

    void ustawPrzesuniecie(int przesuniecie);

    void dodajHaslo(string haslo);

    void usunHaslo(string haslousun);

    void zapiszDoPilku();

    void wyswietlDaneUzytkownika();

    string szyfrowanie(string haslo);

    string odszyfrowanie(string haslo);

    void wyswietlListeHasel();

    void odczytajZPliku();

    void wczytanieZPliku();


};


#endif //PROJEKT_S20717_CEZAR_H
