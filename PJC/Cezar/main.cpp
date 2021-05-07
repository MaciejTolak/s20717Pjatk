#include <iostream>
#include <string>
#include "Cezar.h"
#include "MainHeader.h"

int main() {
    string login, haslo;
    login = "abc";
    haslo = "123";
    Cezar cezar(login, haslo);
    bool dostep;
    cout << "Witamy w programie " << endl;
    dostep = oknoLogowania(login, haslo);
    if (dostep) {
        menuGlowneStart(cezar);
    } else {
        cout << "Podales zle haslo!";
    }

    return 0;
}

bool oknoLogowania(string login, string haslo) {
    string loginTmp, hasloTmp;
    cout << " Login: ";
    cin >> loginTmp;
    cout << "Haslo: ";
    cin >> hasloTmp;
    if (login == loginTmp && haslo == hasloTmp) {
        return true;
    } else {
        return false;
    }

}

void menuGlowne() {

    cout << "Menu Glowne" << endl
         << " 1. Dodaj nowe haslo" << endl
         << " 2. Usun haslo" << endl
         << " 3. Wyswietl wszystkie hasla" << endl
         << " 4. Zapisz do pliku " << endl
         << " 5. Ustaw przesuniecie do enkrypcji" << endl
         << " 6. Odczytaj z pliku" << endl
         << " 7. Wyswietl dane uzytkownika"<<endl
         << " 0. Wylacz program" << endl;


}

void menuGlowneStart(Cezar cezar) {

    int wybor = 1;
    string hasloNowe, hasloDoUsuniecia;
    while (wybor != 0) {
        menuGlowne();
        cin >> wybor;
        switch (wybor) {
            case 1:
                cout << "Nowe haslo: ";
                cin >> hasloNowe;
                cezar.dodajHaslo(hasloNowe);
                break;

            case 2:
                cout << "Haslo do usuniecia: ";
                cin >> hasloDoUsuniecia;
                cezar.usunHaslo(hasloDoUsuniecia);
                break;
            case 3:
                cezar.wyswietlListeHasel();
                break;
            case 4:
                cezar.zapiszDoPilku();
                break;

            case 5:
                int x;
                cout << "Ustaw przesuniecie: ";
                cin >> x;
                cezar.ustawPrzesuniecie(x);

                break;
            case 6:
                cezar.odczytajZPliku();
                break;

            case 7:
                cezar.wyswietlDaneUzytkownika();
                break;

        }
    }

}


