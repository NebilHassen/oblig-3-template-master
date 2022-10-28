package no.oslomet.cs.algdat.Oblig3;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Node<T> p = rot;
        Node <T> q = null;
        int sammenlign = 0;
        while (p != null) { // Denne while-løkken setter først forelder noden/referansen q, og deretter oppdaterer rett verdi til henholdsvis høyre- eller venstrebarn etterhvert som utfallet til if-statementet oppfylles
            q = p;
            sammenlign = comp.compare(verdi, p.verdi);
            if (sammenlign < 0) {
                p=p.venstre;
            }
            else {
                p=p.høyre;
            }
        }

        p = new Node<>(verdi, q); //Kodesnutten fra linje 97-107, lager en ny node. Denne skiller seg fra kodesnutt i 5.2.1 fordi, det trengs en referanse til forelder for at treet skal ha riktig verdi til hver node.
        if (q == null) {
            rot = p; // Denne snutten angir at når q er null, eller tom, så blir rotnoden p.
        } else if (sammenlign < 0) {
            q.venstre = p; //her blir venstrebarn til q lik p
        } else {
            q.høyre = p; //her blir venstrebarn til q lik p
        }
        antall++;
        return true;
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        Node<T> p = rot;
        int teller= 0; //Dette er variablen som inkrementeres etterhvert som compare-metoden under er oppfylles.

        while (p != null) {
            int samnlgn = comp.compare(verdi, p.verdi); //sammenligner verdier og gir oss grunnlaget for å intrementere teller-variablen
            if (samnlgn < 0) { //Oppdaterer venstrebarn hvis compare-metoden gir oss at verdi < p.verdi
                p = p.venstre;
            } else {
                if (samnlgn == 0) teller++; //Intrementerer teller++, og oppdaterer høyrebarn hvis compare-metoden gir oss at verdi == p.verdi
                p = p.høyre;
            }
        }
        return teller;}

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    private static <T> Node<T> førstePostorden(Node<T> p) {
        if (p==null) {throw new NoSuchElementException("p er null!");} // SLik oppgaven angir "vi tar det for gitt at parameteren p ikke er null "
        while (true) {
            if (p.venstre != null) { // Hvis venstrebarnet ikke er null
                p = p.venstre; // Så sett p til å "være"/peke på sitt venstrebarn
            }
            else if (p.høyre != null) { //Hvis høyrebarnet ikke er null
                p = p.høyre; // Så sett p til å peke på sitt høyrebarn
            } else {
                return p;
            }
        }
    }
    private static <T> Node<T> nestePostorden(Node<T> p) { // stor inspirasjon hentet fra 5.1.15.c, også kommentert dette i README
        Node<T> f = p.forelder; // Her så angir vi at f er p sin forelder,
        if (f == null) { // Hvis foreledernoden er null, så return null
            return null;
        }
        if (f != null && (f.høyre == p || f.høyre == null)) { // Hvis f ikke er null og samtidig som at f er sitt høyrebarn er p, mens verdien til barnet er null, så return foreldrenoden
            return f;
        } else {
            return førstePostorden(f.høyre); // else return foreldrenoden sitt høyrebarn (første postordre noden)
        }
    }
    public void postorden(Oppgave<? super T> oppgave) {
        Node<T> p = rot; // Her så får noden p, rot som verdi
        Node<T> forste = førstePostorden(p); // Og vi lager en node som har verdi til førstepostordrenoden til rotnoden gitt over p.
        oppgave.utførOppgave(forste.verdi);
        while (forste.forelder != null) {  // Så lenge foreldre til forste noden, (som er førstepostordrenoden til rotnoden gitt over p) ikke er null så
            forste = nestePostorden(forste); //  Sett noden forste til neste Postordenoden i treet
            if (forste != null){oppgave.utførOppgave(forste.verdi);}
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p == null) {
            return;
        }
        postordenRecursive(p.venstre, oppgave);
        postordenRecursive(p.høyre, oppgave);
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
