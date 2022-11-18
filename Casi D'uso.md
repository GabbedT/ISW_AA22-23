# CASI D'USO

&nbsp;

# Gestione impiegati 

Index: 

* [Assunzione impiegato](#assunzione-impiegato)
* [Licenziamento impiegato](#licenziamento-impiegato)

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
   * Il sistema genera una password unica per l'account del nuovo impiegato 
   * Inserisce i dati nel database

5. Il sistema manda una mail al nuovo impiegato contenente le credenziali di accesso e l'utente viene automaticamente reindirizzato nella schermata di *Home*. Oppure le credenziali vengono semplicemente visualizzate su schermo e una volta cliccato il tasto *X* l'utente viene reindirizzato nella schermata di *Home*. (Scegliere in base alla fattibilità)

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

* Il sistema mostra l'interfaccia a video per il licenziamento dell'impiegato

### Flusso eventi

1. L'utente ricerca l'impiegato all'interno del database inserendo *nome* e *cognome* e preme il tasto *Ricerca*

2. **SE** nel database non viene trovato almeno un dipendente:
   * Il sistema mostra un messaggio di informazione a video 
   * L'utente clicca il tasto conferma
   * Il Sistema mostra nuovamente l'interfaccia 

3. **ALTRIMENTI:** 
   * Il sistema mostra tutti i dipendenti omonimi visualizzandoli a video insieme al loro *codice identificativo*. 

4. L'utente clicca l'apposito tasto *Licenzia* mandando al DBMS aziendale una richiesta di eliminazione

5. L'utente viene reindirizzato nella schermata di *Home*

### Postcondizioni 

* Il sistema ha aggiornato il DBMS aziendale e ha mostrato la schermata *Home* all'utente

---

&nbsp;
