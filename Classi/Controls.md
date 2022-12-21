## UserControl

Gestisce le funzionalità del utente generico

### Funzioni *Private*

* Preleva i dati personali dal DBMS: (**Employee : getPersonalData()**)
* Preleva una notifica dal DBMS: (**Notification : getNotification()**)

### Funzioni *Public*

*Create*

* Visualizza lista notifiche: **createNotificationList()**
* Visualizza schermata home: **createHomeModule()**

*General*

* Conferma la visualizzazione del messaggio: **confirmVisualization()**

  
&nbsp; 

--- 

## EmployeeControl
Gestisce tutte le funzionalità correlate con l'utente impiegato

### Funzioni *Public*: 

*Create*

* Mostra l'interfaccia per visualizzare i dati personali: **createEmployeeDataModule()**
* Mostra l'interfaccia per richiedere un'astensione: **createRequestAbstensionModule()**

*General*

* Rileva presenza: **detectPresence()**
* Rileva presenza da remoto: **detectRemotePresence()**


&nbsp; 

--- 

## EmployerControl

Gestisce tutte le funzionalità correlate con il datore di lavoro

### Funzioni *Private*:

* Preleva un turno in base alla data dal DBMS: (**Shift : getShiftDBMS(SearchData)**)
* Preleva un impiegato dal DBMS: (**Employee : getEmployeeDBMS(SearchData)**) 
* Controlla la validità dei dati del nuovo impiegato: (**Bool : checkInputValidity(Employee)**)
* Aggiungi un impiegato non valido al DBMS: (**addEmployeeDBMS(Employee)**)
* Genera le credenziali per il nuovo impiegato: (**generateCredentials()**)

### Funzioni *Public*:

*Create*

* Mostra l'interfaccia per l'assunzione di un nuovo impiegato: **createHireEmployeeModule()**
* Mostra la turnazione completa: **createShiftModule()**
* Mostra l'interfaccia per la ricerca un impiegato: (**createSearchEmployeeModule()**)
* Mostra i dati dell'impiegato: **createEmployeeDataModule(Employee)**
* Mostra un messaggio di errore: **createErrorMessagePanel(String)**

*General*

* Accetta richiesta di astensione: (**acceptAbstensionRequest(Bool)**)
* Ricerca un impiegato con nome e cognome: (**searchEmployee(String)**)
* Assumi impiegato: **hireEmployee(Employee)**
* Licenzia impiegato: **fireEmployee(Employee)**

&nbsp; 

--- 

## AccountControl
Gestisce tutte le funzionalità correlate con la connessione dell'account

### Funzioni *Private*
* Controlla se le credenziali inserite sono corrette: **Bool : checkCredentialsValidity()**
* Controlla se la vecchia la vecchia password è corretta e le due nuove password inserite corrispondono: **Bool : checkPasswordValidity()**

### Funzioni *Public*

*Create*

* Mostra l'interfaccia di recupero credenziali: **createRetrieveCredentialsModule()**
* Mostra l'interfaccia di login: **createLoginModule()**
* Mostra l'interfaccia home: **createHomeModule()**

*General*

* Login account: **login(Credentials)**
* Logout account: **logout()**
* Modifica password: **modifyPassword(String oldPass, String newPass, String newPassRep)**
  
&nbsp; 

--- 

## SystemControl
Gestisce tutte le funzionalità di sistema (check e azioni periodici)

### Funzioni *Private*

* Chiedi il tempo: **Data : askTime()**
* Calcola lo stipendio orario: (**computeHourlySalary()**)
* Calcola lo stipendio normale in base alle ore: (**computeSalary()**)
* Calcola lo stipendio straordinario: (**computeOvetimeSalary()**)

### Funzioni *Public*
* Genera turnazione: **generateShift()**
* Calcola stipendio: **int : computeSalary(int baseSalary, int expectedHours, int workingHours)**
* Valida account impiegato: **validateAccount(Employee)**
* Elimina account dal DBMS: **deleteAccount(Employee)**
* Invia notifica: **sendNotification()**
* Elimina notifica: **deleteNotification()**
* Riconnettiti al DBMS: **reconnectDBMS()**