package dao;

import java.util.List;

import model.Gerencial;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class GerencialDao {
	 //private HibernateUtil obj_conexao;  
	 
	public List<Gerencial> relatorioGerencial(String nomeMedico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		//Query query = session.createQuery("from RegistroEntrada where medico = :nomeMedico");
		//query.setParameter("medico", nomeMedico);
		
	
		
		SQLQuery query = session.createSQLQuery("select medico as nomeMedico, tipo_entrada as tipoEntrada , count(*) as qtdeEntrada from registro_entrada where medico = :nomeMedico group by tipo_entrada , medico");
		query.setParameter("nomeMedico", nomeMedico);
	    query.addScalar("nomeMedico");
	    query.addScalar("tipoEntrada");
	    query.addScalar("qtdeEntrada");

	    query.setResultTransformer( Transformers.aliasToBean( Gerencial.class ) );

	    List<Gerencial> users = query.list();
	    return users;
		
	
	/*List<Gerencial> array_registros = new ArrayList<Gerencial>();
	
	 
        String sql = "Select medico, tipo_entrada, count(*) from registro_entrada where	medico = :nomeMedico and tipo_entrada= :emergencial group by medico , tipo_entrada ";  
        Statement comando_sql = (Statement) obj_conexao.getConexao()  
                .createStatement();  
        
        ResultSet obj_result = comando_sql.executeQuery(sql);  

        while (obj_result.next()) {  
            Gerencial obj_notas = new Gerencial();  

            //obj_notas.setEletivo(obj_result.getInt("alu_in_ra"));  
            obj_notas.setNomeMedico(obj_result.getString("medico"));  
            obj_notas.setEmergencial(obj_result.getFloat("tipo_entrada"));  
              
              
            array_registros.add(obj_notas);  
        }  */
	
	
	}
}
