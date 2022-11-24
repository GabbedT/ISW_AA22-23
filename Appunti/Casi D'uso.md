# CASI D'USO

## Indice generale  

* [Gestione impiegati](#gestione-impiegati)
* [Gestione accesso account](#gestione-accesso-account)
* [Gestione turni e presenze](#gestione-turni-e-presenze)



&nbsp;



# Gestione impiegati 

Indice: 

* [Assunzione impiegato](#assunzione-impiegato)
* [Licenziamento impiegato (Datore)](#licenziamento-impiegato)
* [Visualizza dati impiegato](#visualizza-dati-impiegato)
* [Modifica stipendio](#modifica-stipendio) (Opzionale)
* [Modifica regime di lavoro](#modifica-regime-di-lavoro) (Opzionale)
* [Visualizza dati impiegato (Impiegato)](#visualizza-dati-impiegato)


&nbsp;

---



## Assunzione impiegato

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di assumere un nuovo impiegato inserendolo all'interno del database aziendale. 

### Attori 

* Datore di lavoro
* DBMS

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema mostra l'interfaccia a video per l'assunzione dell'impiegato

### Flusso eventi

1. L'utente inserisce i dati del nuovo dipendente negli appositi riquadri e preme il tasto *Inserisci* 
 
2. Il sistema effettua un controllo di validità degli input

3. **SE** le credenziali risultano essere errate:
    * Il sistema mostra il messaggio di errore
    * L'utente clicca il tasto conferma
    * Il Sistema mostra nuovamente l'interfaccia 
  
4. **ALTRIMENTI**:
   * Il sistema genera una password unica e un codice identificativo per l'account del nuovo impiegato
   * Inserisce i dati nel database

5. Il sistema manda una mail e una notifica account al nuovo impiegato contenente le credenziali di accesso e l'utente viene automaticamente reindirizzato nella schermata di *Home*. Oppure le credenziali vengono semplicemente visualizzate su schermo e una volta cliccato il tasto *X* l'utente viene reindirizzato nella schermata di *Home*. (Scegliere in base alla fattibilità)

### Postcondizioni 

* Il sistema ha aggiornato il DBMS aziendale con il nuovo account e ha mostrato la schermata *Home* all'utente



---

&nbsp;



## Licenziamento impiegato

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di licenziare un nuovo impiegato eliminandolo dal database aziendale. 

### Attori 

* Datore di lavoro
* DBMS

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema mostra l'interfaccia per la ricerca degli impiegati

### Flusso eventi

1. L'utente ricerca l'impiegato all'interno del database inserendo *nome* e *cognome* e preme il tasto *Ricerca*

2. **SE** nel database non viene trovato almeno un dipendente:
   * Il sistema mostra un messaggio di errore a video 
   * L'utente clicca il tasto conferma
   * Il Sistema mostra nuovamente l'interfaccia 

3. **ALTRIMENTI:** 
   * Il sistema mostra tutti i dipendenti omonimi visualizzandoli a video insieme al loro *codice identificativo*. 

4. L'utente clicca sull'icona del dipendente visualizzando le informazioni di quest'ultimo (vedi caso d'uso [visualizza dati impiegato](#visualizza-dati-impiegato))

5. L'utente clicca l'apposito tasto *Licenzia* mandando al DBMS aziendale una richiesta di eliminazione

6.  **SE** l'operazione di modifica è andata a buon fine: 
    - Il sistema informa l'utente che la modifica è andata a buon fine tramite un messaggio di informazione

7. **ALTRIMENTI:**
   - Il sistema informa l'utente che la modifica non è stata effettuata tramite un messaggio di errore

8. L'utente viene reindirizzato nella schermata di *Home*

### Postcondizioni 

* Il sistema ha aggiornato il DBMS aziendale e ha mostrato la schermata *Home* all'utente



---

&nbsp;



## Visualizza Dati Impiegato

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di visualizzare tutti i dati di un determinato impiegato

### Attori 

* Datore di lavoro
* DBMS

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* L'impiegato deve essere stato ricercato attraverso la barra di ricerca
* Il sistema deve aver mostrato a video l'icona del corrispondente impiegato 

### Flusso eventi

1. L'utente clicca sull'icona del dipendente da visualizzare

2. Il sistema manda una richiesta al DBMS per recuperare tutti i dati del dipendente

3. Viene visualizzata a video l'interfaccia delle informazioni sul dipendente



---

&nbsp;



## Modifica Stipendio

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di modificare lo stipendio di un determinato dipendente

### Attori 

* Datore di lavoro
* DBMS

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema deve aver mostrato a video l'interfaccia delle informazioni sul dipendente (vedi caso d'uso [visualizza dati impiegato](#visualizza-dati-impiegato))

### Flusso eventi

1. L'utente clicca sul riquadro corrispondente allo stipendio

2. L'utente modifica lo stipendio con il nuovo valore 

3. L'utente clicca il pulsante *Conferma modifiche* che è ora diventato disponibile

4. Il sistema manda una richiesta al DBMS per sovrascrivere i vecchi dati 

5. **SE** l'operazione di modifica è andata a buon fine: 
   - Il sistema informa l'utente che la modifica è andata a buon fine tramite un messaggio di informazione

6. **ALTRIMENTI:**
   - Il sistema informa l'utente che la modifica non è stata effettuata tramite un messaggio di errore

7. L'utente clicca sul tasto *OK* del messaggio per confermarne la visione

### Postcondizioni 

Il sistema ha aggiornato il DBMS e l'utente si ritrova nell'interfacccia delle informazioni sul dipendente



---

&nbsp;



## Modifica Regime Di Lavoro

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di modificare il regime di lavoro di un determinato dipendente 

### Attori 

* Datore di lavoro
* DBMS

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema deve aver mostrato a video l'interfaccia delle informazioni sul dipendente (vedi caso d'uso [visualizza dati impiegato](#visualizza-dati-impiegato))

### Flusso eventi

1. L'utente clicca sul riquadro corrispondente al regime di lavoro

2. L'utente modifica il regime di lavoro selezionando una delle due opzioni (FULL-TIME o PART-TIME)

3. L'utente clicca il pulsante *Conferma modifiche* che ora è diventato disponibile

4. Il sistema modifica di conseguenza la turnazione (vedi caso d'uso [modifica turnazione](#modifica-turnazione))

5. Il sistema manda una richiesta al DBMS per sovrascrivere i vecchi dati e i vecchi turni di lavoro

6. **SE** l'operazione di modifica è andata a buon fine: 
   - Il sistema informa l'utente che la modifica è andata a buon fine tramite un messaggio di informazione

7. **ALTRIMENTI:**
   - Il sistema informa l'utente che la modifica non è stata effettuata tramite un messaggio di errore

8. L'utente clicca sul tasto *OK* del messaggio per confermarne la visione

### Postcondizioni 

Il sistema ha aggiornato il DBMS e l'utente si ritrova nell'interfacccia delle informazioni sul dipendente


---

&nbsp;

&nbsp;



# Gestione Accesso Account

Indice: 

* [Login account](#login-account)
* [Logout account](#logout-account)
* [Riconnessione account](#riconnessione-account) (?)
* [Recupera credenziali](#recupera-credenziali)
* [Modifica password](#modifica-password)



&nbsp;

---



## Login Account

Il seguente caso d'uso permette all'utente generico di accedere al proprio account tramite le proprie credenziali

### Attori 

* Utente
* DBMS

### Precondizioni

* Il sistema deve aver mostrato a video la schermata di login
* Il sistema deve aver stabilito una connessione con il DBMS

### Flusso eventi

1. L'utente inserisce le proprie credenziali nell'apposito riquadro di login

2. L'utente clicca sul tasto di login

3. Il sistema manda una richiesta al DBMS

4. **SE** viene trovata una corrispondenza
   * L'utente viene reindirizzato verso la schermata di *Home*

5. **ALTRIMENTI** 
   * Viene visualizzato un messaggio di errore: ("Credenziali errate!")
   * L'utente clicca sul pulsante *OK* per confermare 
   * L'utente ritorna al punto 1

### Postcondizioni 

L'utente si trova sulla schermata di *Home*



&nbsp;

---



## Logout Account

Il seguente caso d'uso permette all'utente generico di uscire dal proprio account 

### Attori 

* Utente

### Precondizioni

* Il sistema deve aver mostrato a video la schermata *Home*
* Il sistema deve aver stabilito una connessione con il DBMS

### Flusso eventi

1. L'utente clicca sul tasto di logout

2. Il sistema esce dall'account del dipendente e mostra a video la schermata iniziale di *Login*

### Postcondizioni 

L'utente si trova sulla schermata di *Login*



---
&nbsp;



## Riconnessione Account 

Il seguente caso d'uso permette all'utente di riconnettersi al proprio account in caso di disconnessione

### Precondizioni 

* L'utente deve essere connesso all'account
* Il sistema deve essere disconnesso dal DBMS 

### Flusso eventi 

1. Il sistema salva la matricola dell'utente loggato e l'interfaccia mostrata a video nel momento della disconnessione

2. Il sistema tenta di stabilire una connessione con il DBMS e tenta di loggare l'utente nuovamente 

3. **SE** il sistema viene riconnesso: 
   * Ritorna all'interfaccia

4. **ALTRIMENTI**: 
   * **SE** è passato una quantità di tempo *X* (da definire)
      * Esci definitivamente dall'account e mostra un messaggio di errore timeout a schermo
   * **ALTRIMENTI**:
      * Ritorna al punto 2

### Postcondizioni

L'utente è loggato nuovamente oppure si trova nella schermata iniziale di login con un messaggio di errore



---



## Recupera Credenziali 

Il seguente caso d'uso permette all'utente generico di recuperare le proprie credenziali di accesso

### Attori 

* Utente 
* DBMS 

### Precondizioni

* Il sistema deve aver mostrato a video la schermata di login

### Flusso eventi

1. L'utente clicca sul tasto *Recupera credenziali*

2. Il sistema mostra l'interfaccia di recupero credenziali 

3. L'utente inserisce la mail collegata al proprio account nell'apposito riquadro e conferma l'inserimento cliccando sul tasto *Conferma*

4. Il sistema controlla l'esistenza dell'email interrogando il DBMS 

5. **SE** l'email non è trovata: 
   - Il sistema mostra a video un messaggio di errore
   - L'utente chiude il messaggio cliccando sul tasto *OK*
   - L'utente ritorna al punto 2

6. **ALTRIMENTI:**
   - Il sistema genera una nuova password e la inserisce all'interno del DBMS
   - Viene mostrata a video una notifica con la nuova password
   - L'utente clicca sul tasto *OK* del messaggio per confermarne la visione

7. L'utente viene reindirizzato nella schermata di login

### Postcondizioni 

Il sistema ha aggiornato il DBMS e l'utente si ritrova nella schermata di login



---

&nbsp;

&nbsp;



# Gestione turni e presenze 

* [Genera turnazione](#genera-turnazione)
* [Rileva presenza](#rileva-presenza)
* [Rileva presenza da remoto](#rileva-presenza-da-remoto)
* [Richiedi assenza](#richiedi-assenza)
* [Gestisci assenza](#gestisci-assenza)(software)
* [Sostituisci impiegato](#sostituisci-impiegato)(software)
* [Richiedi astensione](#richiedi-astensione)
* [Conferma richiesta di astensione](#conferma-richiesta-di-astensione)
* [Modifica richiesta di astensione](#modifica-richiesta-di-astensione)
* [Rifiuta richiesta di astensione](#rifiuta-richiesta-di-astensione)


---

&nbsp;



## Genera Turnazione 

Il seguente caso d'uso permette al datore di lavoro di generare una turnazione per i suoi dipendenti. 

### Attori

* Datore di lavoro
* DBMS
* Tempo 

### Precondizioni 

* L'utente deve essere loggato come "Datore di Lavoro"
* La data deve essere: la settimana prima dell'inizio del mese, il quale deve essere: Gennaio, Aprile, Luglio, Ottobre

### Flusso di eventi 

* Il datore clicca sul tasto *Genera turnazione* 
* Il sistema genera una turnazione coerente
* Il sistema mostra a video la turnazione generata
* Il sistema aggiorna il DBMS e invia una notifica a tutti i dipendenti
* L'utente viene reindirizzato nella schermata di *Home*

### Postcondizioni 

Il DBMS è aggiornato e l'utente è nella schermata di *Home* 



--- 
&nbsp;



## Rileva presenza

Il seguente caso d'uso permette all'impegato di dimostrare la sua presenza in orario sul posto di lavoro.

### Attori 

* Impiegato
* DBMS

### Precondizioni

* L'utente deve essere loggato come "Dipendente"
* L'utente deve confermare la presenza fino a 10 minuti prima dell'orario di ingresso prestabilito dalla turnazione

### Flusso eventi

1. L'utente clicca l'icona "Conferma Presenza".

2. **SE** viene superata la soglia massima per la rilevazione della presenza:
   - Il sistema mostra un messaggio di errore a video: *"Procedere con la rilevazione presenza da remoto"*

3. **ALTRIMENTI:**
   - Il sistema manda una richiesta al DBMS per inserire la presenza del dipendente 
   - Il sistema mostra un messaggio di conferma a video: *"La presenza è stata rilevata correttamente"*

4. L'utente clicca il tasto *Conferma*
   
5. Il sistema mostra la schermata *Home*

### Postcondizioni 

* Il sistema ha aggiornato il DBMS aziendale e ha mostrato la schermata *Home* all'utente



---

&nbsp;



## Rileva presenza da remoto

Il seguente caso d'uso permette all'impegato di dimostrare la sua presenza sul posto di lavoro nel caso in cui esso non sia entrato in orario.

### Attori 

* Impiegato
* DBMS
  
### Precondizioni

* L'utente deve essere loggato come "dipendente"
* L'utente deve aver superato la soglia massima per la rilevazione della presenza
* Il sistema mostra l'interfaccia a video per la rilevazione presenza da remoto

### Flusso eventi

1. L'utente inserisce nell'apposito spazio la motivazione del ritardo.

2. L'utente clicca l'icona "Conferma Presenza".

3. **SE** non viene inserita alcuna motivazione:
   * Il sistema mostra un messaggio di errore a video: *"Inserire la motivazione del ritardo"*
   * L'utente clicca il tasto conferma
   * L'utente ritorna al punto 1

4. **ALTRIMENTI:**
   - Il sistema manda una richiesta al DBMS per inserire la presenza del dipendente 
   - Il sistema mostra un messaggio di conferma a video: *"La presenza è stata rilevata correttamente"*
   
6. Il sistema mostra la schermata *Home*
   
### Postcondizioni 

* Il sistema ha aggiornato il DBMS aziendale e ha mostrato la schermata *Home* all'utente



---
&nbsp;



## Richiedi assenza

Il seguente caso d'uso permette all'impiegato di comunicare la propria assenza

### Attori

* Impigato
* DBMS
* Tempo

### Precondizioni

* L'utente deve essere loggato come "impiegato"
* Il sistema deve aver mostrato a video l'interfaccia di richiesta assenza

### Flusso eventi

1. L'utente inserisce nell'apposito spazio la motivazione dell'assenza

2. L'utente clicca l'icona "Conferma".

3. Se l'utente, non fa la richiesta entro un giorno dall'inizio del turno, viene inviata una segnalazione al datore e al DBMS  

4. **SE** non viene inserita alcuna motivazione:
   * Il sistema mostra un messaggio di errore a video: *"Inserire la motivazione dell'assenza"*
   * L'utente clicca il tasto "conferma"
   * L'utente ritorna al punto 1

4. **ALTRIMENTI**: 

---
&nbsp;



## Gestisci Assenza

Il seguente caso d'uso permette al software di assegnare un sostituto in caso di assenza dipendente.

### Attori 

* DBMS
  
### Precondizioni

 * Il sistema deve aver ricevuto una richiesta di assenza

 ### Flusso eventi

1. Il sistema manda una notifica a tutti i possibili sostituti del turno

2. Una volta che i sostituti hanno accettato la modifica del turno, il sistema seleziona il dipendente con meno ore di lavoro.

3. **SE** Nessun sostituto ha accettato la richiesta:
   - Il sistema considera gli impiegati associati al servizio di priorità superiore esclusi quelli che già sono in turno per quel giorno
   - Il sistema torna al punto 1

3. **ALTRIMENTI**: 
   - Il sistema manda una richiesta al DBMS per la modifica dei turni 
   - Il sistema avvisa il datore di lavoro
   - Il sistema avvisa il sostituto sul nuovo turno

4. L'utente viene indirizzato alla schermata Home

### Postcondizioni 

* Il sistema ha aggiorato il DBMS.



---
&nbsp;



## Conferma Richiesta Di Astensione 

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di confermare la richiesta di astensione mandata da un dipendente

### Attori 

* Datore di lavoro
* DBMS
* Tempo

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema deve aver mostrato a video l'interfaccia della notifica della richiesta di astensione (vedi caso d'uso [visualizza notifica](#visualizza-notifica))
* L'utente deve eseguire il caso d'uso entro un certo periodo di tempo (da definire)

### Flusso eventi

1. L'utente clicca sul pulsante *Conferma richiesta* 

2. Il sistema modifica di conseguenza la turnazione (vedi caso d'uso [modifica turnazione](#modifica-turnazione))

3. Il sistema manda una richiesta al DBMS per sovrascrivere i vecchi turni di lavoro

4. **SE** l'operazione di modifica va a buon fine: 
   - Il sistema informa l'utente che la modifica è andata a buon fine tramite un messaggio di informazione

5. **ALTRIMENTI:**
   - Il sistema informa l'utente che la modifica non è stata effettuata tramite un messaggio di errore
   - L'utente chiude il messaggio cliccando sul tasto *OK*
   - L'utente ritorna al punto 1

6. Il sistema invia un'email e una notifica inbox per informare il dipendente della conferma
   
7.  L'utente clicca sul tasto *OK* del messaggio per confermarne la visione


### Postcondizioni 

Il sistema ha aggiornato il DBMS e l'utente si ritrova nell'interfacccia delle informazioni sul dipendente



---

&nbsp;



## Modifica Richiesta Di Astensione 

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di modificare la richiesta di astensione mandata da un dipendente 

### Attori 

* Datore di lavoro
* DBMS
* Tempo

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema deve aver mostrato a video l'interfaccia della notifica della richiesta di astensione (vedi caso d'uso [visualizza notifica](#visualizza-notifica))
* L'utente deve eseguire il caso d'uso entro un certo periodo di tempo (da definire)

### Flusso eventi

1. L'utente clicca sul pulsante *Modifica richiesta* 

2. L'utente inserisce il nuovo periodo di astensione nell'apposito riquadro 

3. L'utente clicca sul pulsante *Conferma modifiche*

4. Il sistema modifica di conseguenza la turnazione (vedi caso d'uso [modifica turnazione](#modifica-turnazione))

5. Il sistema manda una richiesta al DBMS per sovrascrivere i vecchi turni di lavoro

6. **SE** l'operazione di modifica è andata a buon fine: 
   - Il sistema informa l'utente che la modifica è andata a buon fine tramite un messaggio di informazione

7. **ALTRIMENTI:**
   - Il sistema informa l'utente che la modifica non è stata effettuata tramite un messaggio di errore
   - L'utente chiude il messaggio cliccando sul tasto *OK*
   - L'utente ritornaa al punto 2

8. Il sistema invia un'email e una notifica inbox per informare il dipendente della modifica

8. L'utente clicca sul tasto *OK* del messaggio per confermarne la visione

### Postcondizioni 

Il sistema ha aggiornato il DBMS e l'utente si ritrova nell'interfacccia delle informazioni sul dipendente



---
&nbsp;



## Rifiuta Richiesta Di Astensione 

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di rifiutare la richiesta di astensione mandata da un dipendente

### Attori 

* Datore di lavoro
* DBMS
* Tempo

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema deve aver mostrato a video l'interfaccia della notifica della richiesta di astensione (vedi caso d'uso [visualizza notifica](#visualizza-notifica))
* L'utente deve eseguire il caso d'uso entro un certo periodo di tempo (da definire)

### Flusso eventi

1. L'utente clicca sul pulsante *Rifiuta richiesta* 

2. Il sistema invia un'email e una notifica inbox per informare il dipendente 

4. Il sistema mostra un messaggio di informazione a video per informare l'utente che l'operazione è riuscita.

5. L'utente clicca il tasto *Ok* e viene reindirizzato alla schermata di *Home*
   
3. L'utente clicca sul tasto *OK* del messaggio per confermarne la visione

### Postcondizioni 

L'utente è nella schermata di *Home* 


### Attori 

### Precondizioni

### Flusso eventi

### Postcondizioni 



## Sostituisci Impiegato

### Attori 

* Impiegato
* DBMS
  
### Precondizioni

 * L'utente si trova nel caso d'uso Gestisci Assenza



---
&nbsp;



## Richiedi Astensione

Il seguente caso d'uso permette all'utente con i permessi da "Dipendente" di richiedere un periodo di astensione

### Attori

* Dipendente
* DBMS 
* Tempo 

### Precondizioni 

* L'utente deve essere loggato come "Dipendente"
* L'utente deve eseguire la richiesta entro un periodo di tempo (da specificare) prima del periodo di astensione
* L'utente non deve aver raggiunto il massimo numero di giorni di astensione

### Flusso di eventi

1. L'utente clicca sul tasto *Richiedi Astensione* 

2. L'utente inserisce il periodo di astensione (giorno di inizio e fine) e le motivazioni.

3. L'utente clicca sul tasto di conferma 

4. Il sistema invia una notifica al datore di lavoro e aggiorna i dati sul DBMS 

5. L'utente viene reindirizzato nella schermata di *Home* 