# CASI D'USO 

## Vista d'insieme

* [Gestione Impiegati](#gestione-impiegati)
* [Gestione Accesso Account](#gestione-accesso-account)
* [Gestione Turni](#gestione-turni-e-presenze)
* [Gestione Rilevazione Presenze](#gestione-rilevazione-presenze)
* [Calcolo Stipendio](#calcolo-stipendio)
* [Visualizza Notifiche](#visualizza-notifiche)

&nbsp;


# Gestione Impiegati 

* [Assumi Impiegato](#assumi-impiegato)
* [Licenzia Impiegato](#licenzia-impiegato)

&nbsp; 

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

5. Il sistema mostra il messaggio di stato: "Inserimento dipendente avvenuto con successo!"

6. L'utente clicca il tasto *Ok* 

7. Le credenziali vengono stampate a schermo e il sistema manda una notifica all'account del nuovo impiegato contenente le credenziali di accesso.
   

### Postcondizioni 

* Il sistema ha aggiornato il DBMS aziendale con il nuovo account e ha mostrato la schermata *Home* al datore di lavoro. 

### Errori 

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante l'inserimento dell'impiegato nel database!"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 

* Gli input dell'utente non sono validi:
    - Il sistema mostra il messaggio di errore: "Errore nell'inserimento dei dati!"
    - L'utente clicca il tasto *conferma*
    - Il Sistema mostra nuovamente l'interfaccia 
  

&nbsp;


## Licenzia impiegato

Il seguente caso d'uso permette all'utente avente i permessi da datore di lavoro di licenziare un nuovo impiegato eliminandolo dal database aziendale. 

### Attori 

* Datore di lavoro
* DBMS

### Precondizioni

* L'utente deve essere loggato come "Datore di Lavoro"
* Il sistema ha mostrato l'interfaccia dei dati dell'impiegato da licenziare

### Flusso eventi

1. L'utente clicca l'apposito tasto *Licenzia* mandando al DBMS aziendale una richiesta di eliminazione della riga corrispondente nella tabella degli impiegati

2. Il sistema mostra il messaggio di stato: "Inserimento dipendente avvenuto con successo!"
   
3. L'utente clicca il tasto *Ok*

4. L'utente viene reindirizzato nella schermata di *Home*

### Postcondizioni 

* Il sistema ha aggiornato il DBMS aziendale e ha mostrato la schermata *Home* all'utente

### Errori 

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante l'inserimento dell'impiegato nel database!"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 


&nbsp;


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

4. Il sistema stampa a schermo le informazioni sull'acount dell'utente e la relativa tabella dei turni. 

### Postcondizioni

* Il sistema ha stampato a schermo le informazioni 

### Errori

* Il sistema non riesce a connettersi con il DBMS: 
    - Il sistema mostra il messaggio di errore: "Errore durante l'inserimento dell'impiegato nel database!"
    - L'utente clicca il tasto *Ok*
    - L'utente ritorna al punto 1 