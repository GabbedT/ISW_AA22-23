## UserControl
Gestisce le funzionalità del utente generico

Funzioni *private*
* **Employee : getPersonalData()**

Funzioni *public*
* **confirmedVisualization()**
* **showPersonalData()**
* **retrieveCredentials()**
* **modifyPassword(String oldPass, String newPass, String newPassRep)**
  

## EmployeeControl
Gestisce tutte le funzionalità correlate con l'utente impiegato

Funzioni *public*: 
* **detectPresence()**
* **detectRemotePresence()**


## EmployerControl
Gestisce tutte le funzionalità correlate con il datore di lavoro

Funzioni *private*: 
* Preleva un turno in base alla data: (**Shift : getShift(Data)**)
* Controlla la validità dei dati del nuovo impiegato: (**Bool : checkDataValidity(Employee)**)

Funzioni *public*:
* Accetta richiesta di astensione: (**acceptAbstensionRequest(Bool)**)
* Ricerca un impiegato con nome e cognome: (**searcEmployee(String)**)
* Mostra i dati dell'impiegato: **showEmployeeData(Employee)**
* Mostra la turnazione completa: **showShift()**
* Assumi impiegato: **hireEmployee(Employee)**
* Licenzia impiegato: **fireEmployee(Employee)**


## AccountControl
Gestisce tutte le funzionalità correlate con la connessione dell'account

Funzioni *private*
* Controlla se le credenziali inserite sono corrette: **Bool : checkCredentialsValidity()**

Funzioni *public*
* Login account: **login(Credentials)**
* Logout account: **logout()**
  

## SystemControl
Gestisce tutte le funzionalità di sistema (check e azioni periodici)

Funzioni *public*
* Chiedi il tempo: **Data : askTime()**

Funzioni *public*
* Genera turnazione: **generateShift()**
* Calcola stipendio: **computeSalary(int baseSalary, int expectedHours, int workingHours)**
* Valida account impiegato: **validateAccount(Employee)**
* Elimina account dal DBMS: **deleteAccount(Employee)**
* Invia notifica: **sendNotification()**
* Elimina notifica: **deleteNotification()**
* Riconnettiti al DBMS: **reconnectDBMS()**
