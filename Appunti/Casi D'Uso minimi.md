# CASI D'USO 

## Vista d'insieme

* [Gestione Dipendenti](#gestione-impiegati)
* [Gestione Accesso Account](#gestione-accesso-account)
* [Gestione Turni](#gestione-turni-e-presenze)
* [Gestione Rilevazione Presenze](#gestione-rilevazione-presenze)
* [Visualizza Notifiche](#visualizza-notifiche)

&nbsp;

---

# Gestione Dipendenti 

* [Assumi Dipendente](#assumi-dipendente)
* [Licenzia Dipendente](#licenzia-dipendente)
* [Visualizza Dati Personali](#visualizza-dati-personali)
* [Visualizza Dati Dipendente](#visualizza-dati-dipendente)
* [Cerca Dipendente](#cerca-dipendente)
* [Valida Account Dipendenti](#valida-account-dipendenti)
* [Elimina Account Dipendenti](#elimina-account-dipendenti)
* [Calcola Stipendio](#calcola-stipendio)

&nbsp; 

---


## Assumi Impiegato

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di assumere un nuovo impiegato inserendolo all'interno del database aziendale. 

### Attori 

* Datore di lavoro
* DBMS

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema ha mostrato l'interfaccia a video per l'assunzione dell'impiegato

### Flusso eventi

1. L'utente inserisce i dati del nuovo dipendente negli appositi riquadri e preme il tasto *Inserisci* 
 
2. Il sistema effettua un controllo di validità degli input

3. Il sistema genera una password unica e un codice identificativo per l'account del nuovo impiegato

4. Il sistema inserisce i dati nel database settando la flag per invalidare temporaneamente l'account 

5. Il sistema mostra il messaggio di stato: "Richiesta assunzione dipendente avvenuta con successo! L'assunzione effettiva avverrà tra X giorni" (Il numero di giorni rimanente alla fine del trimestre)

6. L'utente clicca il tasto *Ok* 

7. Le credenziali vengono stampate a schermo e il sistema manda una notifica all'account del nuovo impiegato contenente le credenziali di accesso.
   

### Postcondizioni 

* Il sistema ha aggiornato il DBMS aziendale con il nuovo account e ha mostrato la schermata *Home* al datore di lavoro. 

### Errori 

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 

* Gli input dell'utente non sono validi:
    - Il sistema mostra il messaggio di errore: "Errore nell'inserimento dei dati!"
    - L'utente clicca il tasto *conferma*
    - Il Sistema mostra nuovamente l'interfaccia 
  


&nbsp;

---



## Licenzia Dipendente

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di licenziare un dipendente.

### Attori 

* Datore di lavoro
* DBMS

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema ha mostrato l'interfaccia dei dati dell'impiegato da licenziare (caso d'uso: [visualizza dati dipendente](#visualizza-dati-dipendente))

### Flusso eventi

1. L'utente clicca l'apposito tasto *Licenzia*
   
2. Il sistema mostra il messaggio di informazione: "Sei sicuro di voler licenziare il dipendente?"
   
3. L'utente clicca sul tasto *Si* 
   
4. Il sistema manda al DBMS aziendale una richiesta di modifica della tupla corrispondente nella tabella degli impiegati

5. Il sistema setta il l'attributo di licenziamento a "true"

6. Il sistema mostra il messaggio di stato: "Richiesta licenziamento dipendente avvenuta con successo! Il licenziamento effettivo avverrà tra X giorni" (Il numero di giorni rimanente alla fine del trimestre)
   
7. L'utente clicca il tasto *Ok*

8. L'utente viene reindirizzato nella schermata di *Home*

### Postcondizioni 

* Il sistema ha aggiornato il DBMS aziendale e ha mostrato la schermata *Home* all'utente

### Errori 

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 

* L'utente clicca sul tasto *No*: 
    - Il sistema reindirizza l'utente all'interfaccia dei dati del dipendente



&nbsp;

---



## Visualizza Dati Personali 

Il seguente caso d'uso permette all'utente avente i permessi da impiegato di visualizzare i dati personali compresa l'organizzazione dei suoi turni durante il trimestre.

### Attori 

* Impiegato
* DBMS 

### Precondizioni 

* L'utente deve essere loggato come "Impiegato"
* Il sistema deve aver mostrato l'interfaccia *Home* 

### Flusso Eventi 

1. L'utente clicca sul pulsante *Account* 

2. L'utente clicca sul pulsante *Il mio Account* 

3. Il sistema manda una richiesta al DBMS per prelevare i dati utili

4. Il sistema stampa a schermo le informazioni sull'account dell'utente e la relativa tabella dei turni (vedi [visualizza turnazione](#visualizza-turnazione)). 

### Postcondizioni

* Il sistema ha stampato a schermo le informazioni 

### Errori

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 



&nbsp;

---



## Visualizza Dati Dipendente 

Il seguente caso d'uso permette all'utente avente i permessi da datore lavoro di visualizzare i dati del corrispondente impiegato compresa l'organizzazione dei suoi turni durante il trimestre.

### Attori 

* Datore di lavoro
* DBMS 

### Precondizioni 

* L'utente deve essere loggato come "Datore di Lavoro"
* L'utente deve aver ricercato un impiegato 
* Il sistema deve aver mostrato i risultati della ricerca (caso d'uso: [cerca dipendente](#cerca-dipendente))

### Flusso Eventi 

1. L'utente clicca sull'icona dell'impiegato che vuole visualizzare 

2. Il sistema manda una richiesta al DBMS per prelevare i dati utili

3. Il sistema stampa a schermo le informazioni sull'account dell'utente e la relativa tabella dei turni. 

### Postcondizioni

* Il sistema ha stampato a schermo le informazioni dell'impiegato

### Errori

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 



&nbsp;

---



## Cerca Dipendente

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di ricercare un dipendente con il suo nome e cognome 

### Attori 

* Datore di lavoro
* DBMS 

### Precondizioni 

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema deve aver mostrato l'interfaccia per la ricerca dei dipendenti 

### Flusso Eventi 

1. L'utente clicca nella barra di ricerca e inserisce *nome* e *cognome* oppure *cognome* e *nome* dell'impiegato che vuole ricercare 

2. L'utente clicca sul tasto *Ricerca* 
   
3. Il sistema manda una richiesta al DBMS e estrae tutte le tuple che corrispondono con i criteri di ricerca 

4. Il sistema stampa una lista degli impiegati trovati (potrebbero esserci omonimi oppure l'utente potrebbe aver semplicemente omesso parte del nome e cognome)

### Postcondizioni 

* Il sistema ha stampato a video la lista degli impiegati 

### Errori

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 

* Nel DBMS non si trovano tuple che corispondono con i criteri di ricerca: 
    - Il sistema mostra un messaggio di informazione: "Nessun dipendente trovato nel database!"



&nbsp; 

---



## Valida Account Dipendenti

Il seguente caso d'uso permette al sistema di validare gli account dei dipendenti precedentemente assunti

### Attori

* Tempo
* DBMS 

### Precondizioni 

* Il trimestre deve essere finito (primo giorno del trimestre successivo)

### Flusso Eventi 

* Il sistema manda una richiesta di modifica al DBMS

* Il sistema setta la flag di validità di ogni account a "true" 

### Postcondizioni 

* Il sistema ha validato tutti gli account degli impiegati precedentemente assunti

### Errori

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema semplicemente tenta di riconnettersi al DBMS
    - L'azione viene ripetuta per 50 volte, alla fine delle quali viene mostrato il messaggio di errore: "Errore durante la connessione con il database"


&nbsp; 

---


## Elimina Account Dipendenti

Il seguente caso d'uso permette al sistema di eliminare gli account dei dipendenti dei quali precedentemente il datore di lavoro ne ha richiesto il licenziamento 

### Attori

* Tempo
* DBMS 

### Precondizioni 

* Il trimestre deve essere finito (primo giorno del trimestre successivo)

### Flusso Eventi 

* Il sistema manda una richiesta al DBMS e preleva i dati relativi ai dipendenti

* Il sistema controlla la flag associata al licenziamento

* Il sistema manda una richiesta di eliminazione per tutti i dipendenti che hanno la flag settata

### Postcondizioni 

* Il sistema ha eliminato tutti gli account degli impiegati in attesa del licenziamento

### Errori

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema semplicemente tenta di riconnettersi al DBMS
    - L'azione viene ripetuta per 50 volte, alla fine delle quali viene mostrato il messaggio di errore: "Errore durante la connessione con il database"



&nbsp;

---



## Calcola Stipendio 

Questo caso d'uso permette al sistema di calcolare lo stipendio per un dipendente

### Attori

* Tempo
* DBMS 

### Precondizioni

* Il trimestre deve essere finito (primo giorno del trimestre successivo)

### Flusso Eventi

1. Il sistema legge il numero di ore totale del dipendente
   
2. Calcola il guadagno per ora del dipendente (stipendio base / ore aspettate)
  
3. **SE** il numero di ore è maggiore di quelle aspettate: 
    - Esegui una sottrazione tra le ore in cui ha lavorato e le ore aspettate
    - Calcola il 25% del guadagno ad ora
    - Aggiungi allo stipendio base il prodotto tra il nuovo guadagno ad ora e il numero di ore trovato con la sottrazione (ore stradordinarie)

4. **ALTRIMENTI** (il numero di ore totale è minore di quelle aspettate)
    - Semplicemente moltiplica il guadagno per ora per il numero di ore in cui il dipendente ha lavorato

5. Invia una notifica al dipendente con lo stipendio calcolato

### Postcondizioni 

* La notifica con il nuovo stipendio è arrivata al dipendente


&nbsp; 

# Gestione Accesso Account

* [Login Account](#login-account)
* [Logout Account](#logout-account)
* [Riconnessione Account](#riconnessione-account)
* [Recupera Credenziali](#recupera-credenziali)
* [Modifica Password](#modifica-password)

&nbsp;

---


## Login Account

Il seguente caso d'uso permette all'utente generico di accedere al proprio account tramite le proprie credenziali

### Attori 

* Utente
* DBMS

### Precondizioni

* Il sistema deve aver mostrato a video la schermata di login
* L'utente deve aver cliccato sul tasto *Login* dopo aver inserito le proprie credenziali negli appositi riquadri

### Flusso eventi

1. Il sistema manda una richiesta al DBMS e verifica che le credenziali siano corrette

2. L'utente viene reindirizzato direttamente verso la schermata di *Home* del proprio account

### Postcondizioni 

L'utente si trova sulla schermata di *Home* del proprio account

### Errori 

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 
  
* Se le credenziali inserite non sono corrette: 
   - Viene visualizzato un messaggio di errore: ("Credenziali errate!")
   - L'utente clicca sul pulsante *OK* per confermare 
   - L'utente ritorna al punto 1



&nbsp;

---



## Logout Account

Il seguente caso d'uso permette all'utente generico di uscire dal proprio account 

### Attori 

* Utente

### Precondizioni

* Il sistema deve aver mostrato a video la schermata *Home*
* L'utente deve aver cliccato sul tasto *Logout*

### Flusso eventi

1. Il sistema esce dall'account del dipendente e mostra a video la schermata iniziale di *Login*

### Postcondizioni 

L'utente si trova sulla schermata di *Login*



&nbsp; 

--- 



## Riconnessione Account 

Il seguente caso d'uso permette all'utente di riconnettersi al proprio account in caso di disconnessione

### Attori

* Tempo
* DBMS

### Precondizioni 

* L'utente deve essere connesso all'account
* Il sistema deve essere disconnesso dal DBMS 

### Flusso eventi 

1. Il sistema salva la matricola dell'utente loggato e l'interfaccia mostrata a video nel momento della disconnessione

2. Il sistema tenta di stabilire una connessione con il DBMS e tenta di loggare l'utente nuovamente 

3. Il sistema viene riconnesso

4. Ritorna all'interfaccia

### Postcondizioni

L'utente è loggato nuovamente oppure si trova nella schermata iniziale di login con un messaggio di errore

### Errori 

* Il sistema non riesce a connettersi con il DBMS: 
    - Ritenta la connessione tornando al punto 2
    - Se sono passati 30 secondi dalla perdita della connessione, mostra il messaggio di errore: "Errore: timeout riconnessione"



&nbsp;

---



## Recupera Credenziali 

Il seguente caso d'uso permette all'utente generico di recuperare le proprie credenziali di accesso

### Attori 

* Utente 
* DBMS 

### Precondizioni

* Il sistema deve aver mostrato a video la schermata di login
* L'utente deve aver cliccato sul tasto *Recupera credenziali* 

### Flusso eventi

1. Il sistema mostra l'interfaccia di recupero credenziali 

2. L'utente inserisce la mail collegata al proprio account e la propria matricola nell'apposito riquadro e conferma l'inserimento cliccando sul tasto *Conferma*

3. Il sistema controlla l'esistenza dell'email interrogando il DBMS 

4. Il sistema mostra un messaggio all'utente con la password
   
5. L'utente clicca sul tasto *OK* del messaggio per confermarne la visione

6. L'utente viene reindirizzato nella schermata di login

### Postcondizioni 

Il sistema ha aggiornato il DBMS e l'utente si ritrova nella schermata di login

### Errori

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 
  
* L'email non è trovata nel DBMS: 
    - Il sistema mostra a video un messaggio di errore
    - L'utente chiude il messaggio cliccando sul tasto *OK*
    - L'utente ritorna al punto 2



&nbsp;

---



## Modifica Password

Il seguente caso d'uso permette all'utente di modificare la propria password

### Attori 

* Utente
* DBMS 

### Precondizioni 

* L'utente deve essere connesso all'account 
* L'utente deve aver cliccato sul tasto *Modifica Password*

### Flusso Eventi

1. Il sistema mostra l'interfaccia di recupero password

2. L'utente inserisce la vecchia password nel riquadro corrispondente

3. L'utente inserisce la nuova password nel riquadro corrispondente

4. L'utente reinserisce la nuova password nel riquadro corrispondente

5. Il sistema controlla se le nuove password coincidono

5. Il sistema controlla se la vecchia password inserita è corretta

6. L'utente clicca sul tasto *Conferma*

7. Il sistema stampa il messaggio di stato: "La password è stata cambiata!"

8. L'utente clicca il tasto *Ok*

9. Il sistema reindirizza l'utente alla schermata di *Home* 

### Postcondizioni 

* L'utente si trova sulla schermata di *Home* 

### Errori 

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 

* La vecchia password inserita non è corretta: 
    - Il sistema mostra il messaggio di errore: "Errore: password inserita errata!"
    - L'utente ritorna al punto 1

* Le nuove password non coincidono: 
    - Il sistema mostra il messaggio di errore: "Errore: le nuove password non coincidono"



&nbsp; 

---



# Gestione Turni

* [Richiedi Astensione](#richiedi-astensione)
* [Conferma Richiesta Di Astensione](#conferma-richiesta-di-astensione)
* [Rifiuta Richiesta Di Astensione](#rifiuta-richiesta-di-astensione)
* [Visualizza Turnazione Completa](#visualizza-turnazione-completa)
* [Genera Turnazione](#genera-turnazione)


&nbsp;

---


## Richiedi Astensione

Il seguente caso d'uso permette all'utente con i permessi da "Dipendente" di richiedere un periodo di astensione

### Attori

* Dipendente
* DBMS 

### Precondizioni 

* L'utente deve essere loggato come "Dipendente"
* L'utente non deve aver raggiunto il massimo numero di giorni di astensione
* L'utente deve aver cliccato sul tasto *Richiedi Astensione*

### Flusso di eventi

1. Il sistema mostra l'interfaccia della richiesta astensione 

2. L'utente inserisce il periodo di astensione (giorno di inizio e fine) e le motivazioni.

3. L'utente clicca sul tasto *Conferma*  

4. Il sistema invia una notifica al datore di lavoro e aggiorna i dati sul DBMS 

5. L'utente viene reindirizzato nella schermata di *Home*

### Postcondizioni 

* L'utente si trova sulla schermata di *Home* 

### Errori 

* L'utente inserisce una data che è compresa nel trimestre corrente: 
    - Il sistema mostra il messaggio di errore: "Errore inserimento periodo: inserisci una data dopo il X/X/X" (giorno della fine del trimestre)



&nbsp;

---



## Conferma Richiesta Di Astensione 

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di confermare la richiesta di astensione mandata da un dipendente

### Attori 

* Datore di lavoro
* DBMS

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema deve aver mostrato a video l'interfaccia della notifica della richiesta di astensione (vedi caso d'uso [visualizza notifica](#visualizza-notifica))
* L'utente deve aver cliccato sul tasto *Conferma Richiesta* 

### Flusso eventi

1. Il sistema manda una richiesta al DBMS per confermare le ferie

2. Il sistema mostra il messaggio di informazione: "La richiesta è stata accettata con successo!"

3.  L'utente clicca sul tasto *OK* del messaggio per confermarne la visione

4. Il sistema invia una notifica per informare il dipendente della conferma

### Postcondizioni 

Il sistema ha aggiornato il DBMS e l'utente si ritrova nella schermata di *Home*

### Errori

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 



&nbsp;

---



## Rifiuta Richiesta Di Astensione 

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di rifiutare la richiesta di astensione mandata da un dipendente

### Attori 

* Datore di lavoro
* DBMS

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema deve aver mostrato a video l'interfaccia della notifica della richiesta di astensione (vedi caso d'uso [visualizza notifica](#visualizza-notifica))
* L'utente deve aver cliccato sul tasto *Rifiuta Richiesta*

### Flusso eventi

1. Il sistema invia una notifica inbox per informare il dipendente 

2. Il sistema mostra un messaggio di informazione a video per informare l'utente che l'operazione è riuscita.

3. L'utente clicca il tasto *Ok* e viene reindirizzato alla schermata di *Home*
   
4. L'utente clicca sul tasto *OK* del messaggio per confermarne la visione

### Postcondizioni 

L'utente è nella schermata di *Home*

### Errori

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 



&nbsp;

--- 



## Visualizza Turnazione Completa 

Il seguente caso d'uso permette all'utente con i permessi da "Datore di lavoro" di visualizzare l'intera turnazione trimestrale 

### Attori 

* Datore di lavoro
* DBMS

### Precondizioni

* L'utente deve essere loggato come "Datore di lavoro"
* L'utente deve aver cliccato sull'icona *Visualizza Trimestre*

### Flusso di eventi

1. Il sistema richiede al DBMS per ogni servizio offerto dall'azienda tutti i turni di tutto il trimestre

2. Il sistema mostra a video una tabella con tutte le informazioni sui turni di tutto il trimestre 

### Postcondizioni

* La tabella della turnazione è stata generata 

### Errori

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 



&nbsp;

---


## Genera Turnazione

Il seguente caso d'uso permette al sistema di generare una nuova turnazione.

### Attori 

* Tempo 
* DBMS

### Precondizioni

* Il trimestre deve essere finito (primo giorno del trimestre successivo)

### Flusso Eventi 

1. Genera turnazione per il servizio X 

2. Seleziona tra i 4 dipendenti 2 che devono lavorare la mattina e 2 che devono lavorare il pomeriggio

3. Riempi tutti i turni di tutte le giornate (ECCETTO QUELLI CON PERIODI DI ASTENSIONE) con i dipendenti prelevati (Procedi sequenzialmente (inizia dal primo e passa al successivo))

4. Se per un periodo manca un dipendente per astensione, preleva un dipendente random che non lavora nella stessa fascia oraria. 

5. Controlla che al dipendente non coincidano i periodi di astensione con quello che deve sostituire

6. Riempi i periodi di astensione con il dipendente sostitutivo

7. Ritorna al punto 3 e passa al dipendente successivo. Se tutti i dipendenti sono stati turnati ritorna al punto 1 e passa al servizio successivo. Se tutti i servizi sono stati turnati vai avanti

8. Fine.

  *DOMANDE*
Dobbiamo presupporre che il datore faccia errori sul permesso di astensione? (Accetti l'astensione di almeno 3 persone dello stesso servizio nello stesso periodo)

I ritardi devono essere sostituiti? 



&nbsp;

---



# Gestione Rilevazione Presenze

* [Rileva Presenza](#rileva-presenza)
* [Rileva Presenza Da Remoto](#rileva-presenza-da-remoto)


&nbsp;

---


## Rileva Presenza

Il seguente caso d'uso permette al dipendente di dimostrare la sua presenza in orario sul posto di lavoro.

### Attori 

* DBMS
* Tempo

### Precondizioni

* Il sistema ha mandato un messaggio di conferma quando il dipendente ha badgato
* Il dipendente deve aver badgato entro 10 minuti dall'inizio del turno

### Flusso eventi

1. Il messaggio di conferma con le informazioni sul turno e sul dipendente viene ricevuto

2. Scrivi sul corrispondente turno nel DBMS l'informazione sulla rilevazione della presenza (in orario)

### Postcondizioni 

* Il sistema ha aggiornato il DBMS aziendale 



&nbsp;

---



## Rileva Presenza Da Remoto

Il seguente caso d'uso permette al dipendente di dimostrare la sua presenza in orario sul posto di lavoro.

### Attori 

* Dipendente
* DBMS
* Tempo

### Precondizioni

* Il sistema ha mandato un messaggio di conferma quando il dipendente ha badgato
* Il dipendente ha sforato il tempo limite per l'ingresso in orario
* Il dipendente ha cliccato sul tasto *Rileva Presenza Da Remoto*

### Flusso eventi

1. Il messaggio di conferma con le informazioni sul turno e sul dipendente viene ricevuto

2. Scrivi sul corrispondente turno nel DBMS l'informazione sulla rilevazione della presenza (in ritardo)

### Postcondizioni 

* Il sistema ha aggiornato il DBMS aziendale e ha mostrato la schermata *Home* all'utente

### Errori

* L'utente ha cliccato sul tasto *Rileva Presenza Da Remoto* prima dello scadere del tempo limite:
    - Il sistema mostra il messaggio di errore: "Errore: la rilevazione della presenza da remoto è concessa solo dopo le ore: (stampa l'ora limite della rilevazione presenza)"


&nbsp;

---


# Gestione Notifiche 

* [Visualizza Lista Notifiche](#visualizza-lista-notifiche)
* [Visualizza Notifica](#visualizza-notifica)
* [Invia Notifica](#invia-notifica)
* [Elimina Notifica](#elimina-notifica)

&nbsp;

---


## Visualizza Lista Notifiche

Il seguente caso d'uso permette al generico utente di visualizzare la lista delle notifiche

### Attori

* Utente
* DBMS 

### Precondizioni 

* L'utente ha cliccato sul tasto per *visualizzare tutte le notifiche*

### Flusso Eventi 

1. Il sistema manda una richiesta di lettura al DBMS per ogni notifica associata all'utente

2. Il sistema stampa a schermo solo il nome e il tipo della notifica per ogni notifica prelevata dal DBMS

### Postcondizioni

* Il sistema ha mostrato all'utente la lista delle notifiche

### Errori 

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 



&nbsp;

---



## Visualizza Notifica 

Il seguente caso d'uso permette al generico utente di visualizzare una notifica

### Attori 

* Utente 
* DBMS 

### Precondizioni 

* L'utente ha [visualizzato la lista delle notifiche](#visualizza-lista-notifiche) inbox e cliccato sulla notifica da visualizzare

### Flusso Eventi

1. Il sistema manda una richiesta di lettura al DBMS 

2. Il sistema stampa a schermo tutte le informazioni sulla notifica

### Postcondizioni 

* Il sistema ha mostrato all'utente la notifica

### Errori 

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 



&nbsp;

---



## Invia Notifica 

Il seguente caso d'uso permette al sistema di inviare una notifica ad un generico utente

### Attori 

* DBMS 

### Precondizioni

* Il sistema ha ricevuto una richiesta di invio notifica

### Flusso Eventi

1. Riempi i campi della notifica e setta il tipo 

2. Manda una richiesta al DBMS per creare un'entità notifica

### Postcondizioni 

* Il sistema ha creato una nuova notifica

### Errori 

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 



&nbsp;

---



## Elimina Notifica 

Il seguente caso d'uso permette al sistema di eliminare una notifica

### Attori 

* Tempo
* DBMS 

### Precondizioni

* Il sistema ha ricevuto una richiesta periodica di controllo notifiche

### Flusso Eventi

1. Il sistema manda una richiesta di lettura al DBMS 

2. Per ogni notifica letta controlla lo stato (letta oppure non letta)

3. **SE** la notifica è stata letta:
    - Controlla la data di lettura
    - Confronta con la data del sistema
    - Elimina la notifica se la differenza tra la data del sistema e quella di lettura è maggiore di 1 mese

4. **ALTRIMENTI**:
    - Non effettuare alcuna eliminazione 

### Postcondizioni 

* Il sistema ha eliminato una notifica

### Errori 

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante la connessione con il database"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 