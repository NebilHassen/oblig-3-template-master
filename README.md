# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Nabil Hassen, S336115, s33611@oslomet.no


# Oppgavebeskrivelse

Mye av oppgave 1 kommer fra tipset gitt i oppgaven, 5.2.3a).

I oppgave 1 så gikk jeg frem ved å instansiere noden p som rotnoden, og noden q som en tom hjelpenode (og videre en sammenligningsvariabel, kalt sammenlign).
Her så angir jeg i en while-løkke at så lenge rotnoden ikke er tom så blir den tomme hjelpenoden min p, lik rotnoden p, ettersom jeg vil at q skal være en uendret "forelder" til p, 
dette blir viktig senere ettersom variablen p blir endret. 
Deretter så endrer jeg på p, og gjør den enten lik p.venstre (til sitt eget barn, til venstre) eller lik p.høyre (gjør den om til sitt eget barn, til høyre), avhengig av utfallet 
til sammenligningen som skjer i comparator() fra Treeset-klassen (gir 0 hvis to objekter er like, -1 hvis høyre er mindre en venstre, og 1 hvis venstre er større enn høyre )


Mye av oppgave 2 kommer fra tipset gitt i oppgaven, 5.2.6 oppgave 2 og kodesnutten i boka som heter "inneholder (T verdi)"
I oppgave 2 så brukte en hjelpevariable teller, som ble intrekmentert etterhvert som compare-metoden i linjene under er oppfylles. 
Hver sammenligning baserer seg på utfallet av samnlgn eller compare-metoden sitt utfall, der første baserer eg på at sammenligningen ikke oppfylles, og venstrebarnets verdi oppdateres/settes
Mens andre sammenligning (else setningen) så antass det at sammenlignen har 2 like objekter som sammenlignes, og dermed så intrekmenteres teller med 1. 

 
Oppgave 3, ser veldig mye ut som eksempel 5.1.7 g) og 5.1.15. c) i boka deler av min løsning er hentet derfra:
I oppgave 3 så begynte jeg med å teste om parameteren innsatt er null, hvis den var det vil det kastes en exception. 
Videre så har jeg i en if-else setning først forsøkt å hentet fram et venstrebarn (hver gang metoden kalles), hvis venstrebarnet ikke er null, så peker/"blir" p mot p sitt venstrebarn.
Hvis p.venstre skulle være null, så skjer det samme bare med p sitt høyrebarn.



Oppgave 4, ser veldig mye ut som eksempel 5.1.7 g) og 5.1.15. c) 
Videre så har jeg også brukt 5.1.11 og 5.1.12 i boka deler av min løsning er hentet derfra:
I oppgave 4 så har jeg satt metoden til å kjøre på verdien p.venstre og p.høyre slik at metoden først tok utagngspunkt i barnet deres ved igangsettelse av oppgave oppgave.
