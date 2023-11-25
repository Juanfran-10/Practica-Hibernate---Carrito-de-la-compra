package controladores;

import controladores.NewHibernateUtil;
import Modelos.Clientes;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CtrlClientes {
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

    public void guardaCliente(Clientes cliente) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.save(cliente);
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

    public void eliminaCliente(Clientes cliente) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.delete(cliente);
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

    public void actualizaCliente(Clientes cliente) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.update(cliente);
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

    public Clientes obtenCliente(String idCliente) throws HibernateException {
        Clientes cliente = null;
        try {
            iniciaOperacion();
            cliente = (Clientes) sesion.get(Clientes.class, idCliente);
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
        return cliente;
    }

    public List<Clientes> obtenerListaClientes() throws HibernateException {
        List<Clientes> listaClientes = null;
        try {
            iniciaOperacion();
            listaClientes = sesion.createQuery("from Clientes").list();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
        return listaClientes;
    }
}//Fin de clase CtrlClientes
