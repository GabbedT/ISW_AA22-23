## Home 

**HomeModule** 

Mette a disposizione tutti i servizi principali dell'interfaccia *Home*

* Logout: (**logout()**)
* Visualizza notifiche: (**showNotificationsModule()**)
* Modifica password: (**showModifyPasswordModule()**)

L'interfaccia home non ha il tasto per andare nell'interfaccia precedente

&nbsp; 

--- 

**EmployerHomeModule**: generalizzata da **HomeModule**

Mette a disposizione tutti i servizi principali del datore di lavoro

* Assumi impiegato: (**showHireEmployeeModule()**)
* Visualizza turnazione: (**showShiftModule()**)
* Cerca dipendente: (**showSearchEmployeeModule()**)

&nbsp; 

--- 

**EmployeeHomeModule**: generalizzata da **HomeModule**

Mette a disposizione tutti i servizi principali del datore di lavoro

* Rileva presenza da remoto: (**showDetectRemotePresenceModule()**)
* Visualizza dati personali: (**showEmployeeDataModule()**)
* Richiedi astensione: (**showRequestAbstensionModule()**)

&nbsp; 

--- 


## Controllo Sistema

**DBMSBoundary**

Si interfaccia con il DBMS, esegue:

* Queries (**query(Data)**) 
* Modifiche (**modify(Data)**) 
* Inserimenti (**insert(Data)**) 
* Eliminazioni (**delete(Data)**)

&nbsp; 

--- 

**SystemBoundary**

Si crea all'inizio del programma, interagisce con il tempo e abilita i servizi che dipendono da esso:

* Richiedi il tempo di sistema: (**Time : askTime()**)
* Generare la turnazione:  (**generateShift()**)
* Eliminare l'account di un dipendente licenziato: (**deleteAccount(Employee)**) 
* Confermare l'assunzione di un dipendente assunto: (**validateAccount(Employee)**)
* Rilevazione presenza da remoto: (**detectRemotePresence()**)

&nbsp; 

--- 

## Informazione Video

Un messaggio non ha il tasto per andare nell'interfaccia precedente

**MessagePanel**

Messaggio generico stampato a video:

* Stampa messaggio: (**showMsg(String)**)
* Conferma visualizzazione: (**confirmVisualization()**)

&nbsp; 

--- 

**ErrorMessagePanel**: generalizzata da **MessagePanel** 

Stampa un messaggio di errore producendo un suono.  

&nbsp; 

--- 

**InfoMessagePanel**: generalizzata da **MessagePanel**

Stampa un messaggio di informazione.

&nbsp; 

--- 

## Gestione Account

**LoginModule** 

Mette a disposizione il servizio di login ad un generico utente

* Conferma le credenziali: (**confirmLoginCredentials()**)
* Recupera le credenziali: (**showRetrieveCredentialsModule()**)

&nbsp; 

--- 

**RetrieveCredentialsModule**

Mette a disposizione il servizio per recuperare le credenziali ad un generico utente

* Conferma le informazioni per il recupero (**confirmInfo()**)

&nbsp; 

--- 

**ModifyPasswordModule**

Mette a disposizione il servizio per modificare la password ad un generico utente. 

* Conferma le informazioni per il recupero (**confirmInfo()**)

&nbsp; 

--- 

## Gestione Dipendenti

**HireEmployeeModule**

Mette a disposizione un'interfaccia per specificare le informazioni sul nuovo impiegato da assumere.

* Conferma le informazioni per l'assunzione (**confirmHire()**)

&nbsp; 

--- 

**EmployeeDataModule**

Mette a disposizione un'interfaccia per visualizzare i dati dell'impiegato, è un'interfaccia utilizzabile sia per visualizzare dati di altri impiegati (dal datore) sia per visualizzare i propri dati (dall'utente). 

* Licenzia dipendente se a visualizzare l'interfaccia è il datore di lavoro: (**fireEmployee()**)

&nbsp; 

--- 

**SearchEmployeeModule**

Mette a disposizione un'interfaccia per la ricerca del dipendente. 

* Conferma i criteri di ricerca: (**confirmSearchCriteria()**)

&nbsp; 

--- 


## Gestione Turni

**AbstentionRequestModule**

Mette a disposizione un'interfaccia per la richiesta di astensione per il dipendente. 

* Conferma i data per la richiesta: (**confirmAbstentionRequest()**)

&nbsp; 

--- 

## Gestione Notifiche

**GenericNotificationModule**

Mette a disposizione un'interfaccia per visualizzare una generica notifica.  

&nbsp; 

--- 

**AbstentionRequestNotificationModule**

Mette a disposizione un'interfaccia (esclusiva per il datore di lavoro) per visualizzare una richiesta di astensione del dipendente. 

* Accetta la richiesta: (**acceptAbstentionRequest()**)
* Rifiuta la richiesta: (**declineAbstentionRequest()**)