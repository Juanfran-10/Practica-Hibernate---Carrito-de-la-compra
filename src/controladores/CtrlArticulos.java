package controladores;

import Modelos.Articulos;
import Modelos.Facturas;
import Modelos.Familias;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CtrlArticulos {
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

    public void guardaArticulo(Articulos articulo) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.save(articulo);
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
    
    public void eliminaArticulo(Articulos articulo) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.delete(articulo);
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
    
    public void actualizaArticulo(Articulos articulo) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.update(articulo);
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
    
    public Articulos obtenArticulo(String idArticulo) throws HibernateException {
        Articulos articulo = null;
        try {
            iniciaOperacion();
            articulo = (Articulos) sesion.get(Articulos.class, idArticulo);
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
        return articulo;
    }
    
    public List<Articulos> obtenerListaArticulos() throws HibernateException {
        List<Articulos> listaArticulos = null;
        try {
            iniciaOperacion();
            Criteria criteria = sesion.createCriteria(Articulos.class);
            listaArticulos = criteria.list();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
        return listaArticulos;
    }
}//Fin de clase CtrlArticulos
