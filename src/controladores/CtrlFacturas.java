package controladores;

import Modelos.Facturas;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CtrlFacturas {

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
    
    public void guardaFactura(Facturas factura) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.save(factura);
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
    
    public void eliminaFactura(Facturas factura) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.delete(factura);
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
    
    public void actualizaFactura(Facturas factura) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.update(factura);
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
    
    public Facturas obtenFactura(int numFactura) throws HibernateException {
        Facturas factura = null;
        try {
            iniciaOperacion();
            factura = (Facturas) sesion.get(Facturas.class, numFactura);
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
        return factura;
    }

    public List<Facturas> obtenerListaFacturas() throws HibernateException {
        List<Facturas> listaFacturas = null;
        try {
            iniciaOperacion();
            Criteria criteria = sesion.createCriteria(Facturas.class);
            listaFacturas = criteria.list();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
        return listaFacturas;
    }
}//Fin de clase CtrlFacturas
