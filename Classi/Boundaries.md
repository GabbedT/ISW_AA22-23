## Home 

L'interfaccia home non ha il tasto per andare nell'interfaccia precedente

**EmployerHomeModule**: generalizzata da **HomeModule**
Mette a disposizione tutti i servizi principali del datore di lavoro

* Assumi impiegato: (*showHireEmployeeModule()*)
* Visualizza turnazione: (*showShiftModule()*)
* Cerca dipendente: (*showSearchEmployeeModule()*)
* Visualizza dati personali: (*showPersonalDataModule()*)
* Logout: (*logout()*)
* Visualizza notifiche (campanella): (*showNotificationsModule()*)
* Modifica password: (*showModifyPasswordModule()*)

**EmployeeHomeModule**: generalizzata da **HomeModule**
Mette a disposizione tutti i servizi principali del datore di lavoro

* Rileva presenza da remoto: (*showDetectRemotePresenceModule()*)
* Visualizza dati personali: (*showPersonalDataModule()*)
* Richiedi astensione: (*showRequestAbstensionModule()*)
* Logout: (*logout()*)
* Modifica password: (*showModifyPasswordModule()*)
* Visualizza notifiche: (*showNotificationsModule()*)


## Controllo Sistema

**DBMSBoundary**
Si interfaccia con il DBMS, esegue queries (*query()*), modifiche, inserimenti (*insert(Data)*) e eliminazioni (*delete(Data)*)

**SystemBoundary**
Si crea all'inizio del programma, interagisce con il tempo (*Time : askTime()*), in particolare per: 
    * Abilitare i servizi software trimestrali (generare turnazioni (*generateShift()*), licenziamenti (*fireEmployees()*) e assunzioni (*hireEmployess()*))
    * Abilitare i servizi di rilevazione presenza (*enableDetectRemotePresence()*)
    * Abilitare i check periodici


## Informazione Video

Un messaggio non ha il tasto per andare nell'interfaccia precedente

**ErrorMessagePanel**: generalizzata da **MessagePanel** 
Stampa un messaggio di errore, l'utente può confermare attraverso il tasto *Ok* (*confirmVisualization()*).  

**InfoMessagePanel**: generalizzata da **MessagePanel**
Stampa un messaggio di informazione (*showMsg(String)*), l'utente può confermare attraverso il tasto *Ok* (*confirmVisualization()*). Dopo lo stesso modulo può anche 


## Gestione Account

**LoginModule**: 
Mette a disposizione il servizio di login ad un generico utente. L'utente può confermare il login attraverso l'apposito tasto *Conferma* (*confirmLoginCredentials()*)

**RetrieveCredentialsModule**
Mette a disposizione il servizio per recuperare le credenziali ad un generico utente. L'utente può confermare le info per il recupero attraverso l'apposito tasto *Conferma* (*confirmInfo()*)

**ModifyPasswordModule**
Mette a disposizione il servizio per modificare la password ad un generico utente. L'utente può confermare le info per la modifica attraverso l'apposito tasto *Conferma* (*confirmInfo()*)


## Gestione Dipendenti

**HireEmployeeModule**
Mette a disposizione un'interfaccia per specificare le informazioni sul nuovo impiegato da assumere. Il datore può confermare gli input attraverso l'apposito tasto *Conferma Assunzione* (*confirmHire()*)

**EmployeeProfileModule**
Mette a disposizione un'interfaccia per visualizzare i dati dell'impiegato, è un'interfaccia utilizzabile sia per visualizzare dati di altri impiegati (dal datore) sia per visualizzare i propri dati (dall'utente). Se a visualizzare l'interfaccia è il datore allora l'interfaccia mostra un tasto *Licenzia Dipendente* (*fireEmployee()*)

**SearchEmployeeModule**
Mette a disposizione un'interfaccia per la ricerca del dipendente. Il datore può confermare i criteri di ricerca tramite l'apposito tasto *Conferma* (a forma di lente di ingrandimento) (*confirmSearchCriteria()*)


## Gestione Turni

**AbstentionRequestModule**
Mette a disposizione un'interfaccia per la richiesta di astensione per il dipendente. Il dipendente può confermare i dati inseriti tramite l'apposito tasto *Conferma* (*confirmAbstentionRequest()*)


## Gestione Notifiche

**GenericNotificationModule**
Mette a disposizione un'interfaccia per visualizzare una generica notifica.  

**AbstentionRequestNotificationModule**
Mette a disposizione un'interfaccia (esclusiva per il datore di lavoro) per visualizzare una richiesta di astensione del dipendente. Il datore tramite il tasto *Accetta* (*acceptAbstentionRequest(true)*) accetta la richiesta mentre tramite il tasto *Rifiuta* (*declineAbstentionRequest()*) 