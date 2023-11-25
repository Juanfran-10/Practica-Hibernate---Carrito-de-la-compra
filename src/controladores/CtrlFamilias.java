package controladores;

import controladores.NewHibernateUtil;
import Modelos.Familias;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CtrlFamilias {

    //Atributos
    private Session sesion;
    private Transaction tr;

    private void iniciaOperacion() throws HibernateException {
        sesion = NewHibernateUtil.getSessionFactory().openSession();
        tr = sesion.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tr.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public void guardaFamilia(Familias familia) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.save(familia);
            tr.commit();
        } catch (HibernateException he) {
            if (tr != null) {
                tr.rollback();
            }
            manejaExcepcion(he);
            throw he;
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public void eliminaFamilia(Familias familia) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.delete(familia);
            tr.commit();
        } catch (HibernateException he) {
            if (tr != null) {
                tr.rollback();
            }
            manejaExcepcion(he);
            throw he;
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public void actualizaFamilia(Familias familia) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.update(familia);
            tr.commit();
        } catch (HibernateException he) {
            if (tr != null) {
                tr.rollback();
            }
            manejaExcepcion(he);
            throw he;
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    public Familias obtenFamilia(String idFamilia) throws HibernateException {
        Familias familia = null;
        try {
            iniciaOperacion();
            familia = (Familias) sesion.get(Familias.class, idFamilia);
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
        return familia;
    }

    public List<Familias> obtenerListaFamilias() throws HibernateException {
        List<Familias> listaFamilias = null;
        try {
            iniciaOperacion();
            listaFamilias = sesion.createQuery("from Familias").list();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
        return listaFamilias;
    }

}//Fin de clase CtrlFamilias
