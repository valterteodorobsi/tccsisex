package dao;

import java.util.List;

import model.Funcionario;
import model.Prontuario;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.jgroups.util.Promise;

import controller.ProntuarioController;
import util.HibernateUtil;

public class ProntuarioDaoImp implements ProntuarioDao {

	// / ver o controle de matricula repitida e ver o delete
	public void save(Prontuario prontuario) throws Exception {
	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try {
			SQLQuery query = session
					.createSQLQuery("insert into Prontuario (id_matricula, PENDENCIAS)"
							+ " values( :id_matricula, :PENDENCIAS)");
			query.setParameter("id_matricula", prontuario.getid_matricula());
			query.setParameter("PENDENCIAS", prontuario.getPENDENCIAS());
			query.executeUpdate();

		} catch (Exception ex) {

			// session.refresh(funcionario);
			session.close();
			ProntuarioController.matriculaErro();
			throw ex;

		}
		t.commit();
	}

	public List<Prontuario> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Prontuario").list();
		t.commit();
		return lista;
	}

	public void remove(Prontuario prontuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(prontuario);
		t.commit();
	}

	public void update(Prontuario prontuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session
				.createSQLQuery("update Prontuario set PENDENCIAS= :PENDENCIAS where ID_MATRICULA = :id_matricula");
		query.setParameter("id_matricula", prontuario.getid_matricula());
		query.setParameter("PENDENCIAS", prontuario.getPENDENCIAS());

		query.executeUpdate();

		t.commit();

	}

	public List<Prontuario> pesquisarPorMatricula(Integer matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session
				.createSQLQuery("select funcionario.nome as nomeFuncionario, funcao.nome as nomeFuncao, setor.nome as nomeSetor, funcionario.data_nasc, funcionario.rg, funcionario.email, funcionario.ramal, prontuarios.id_matricula, prontuarios.pendencias , prontuarios.ID_PRONTUARIO  from prontuario as prontuarios inner join FUNCIONARIO as funcionario on funcionario.ID_MATRICULA = prontuarios.id_matricula inner join SETOR as setor on funcionario.ID_CENTRO_CUSTO = setor.ID_CENTRO_CUSTO inner join FUNCAO as funcao on funcionario.ID_FUNCAO = funcao.ID_FUNCAO where prontuarios.id_matricula = :matricula group by funcionario.nome, funcao.nome, setor.nome, funcionario.data_nasc, funcionario.rg, funcionario.email, funcionario.ramal, prontuarios.id_matricula, prontuarios.pendencias, prontuarios.ID_PRONTUARIO  ");
		query.setParameter("matricula", matricula);
		query.addScalar("nomeFuncionario");
		query.addScalar("nomeFuncao");
		query.addScalar("nomeSetor");
		query.addScalar("data_nasc");
		query.addScalar("email");
		query.addScalar("rg");
		query.addScalar("ramal");
		query.addScalar("id_matricula");
		query.addScalar("PENDENCIAS");
		query.addScalar("ID_PRONTUARIO");
		query.setResultTransformer(Transformers.aliasToBean(Prontuario.class));
		List<Prontuario> users = query.list();
		t.commit();
		return users;
	}

	public List<Funcionario> pesquisarPorMatriculaFuncionario(
			Integer id_matricula) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("from Funcionario f where f.ID_MATRICULA = :id_matricula");
		query.setParameter("id_matricula", id_matricula);
		t.commit();
		return query.list();
	}

	public List<Prontuario> pesquisarNome() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		SQLQuery query = session
				.createSQLQuery("select funcionario.nome as nomeFuncionario, funcionario.id_matricula, funcao.nome as nomeFuncao, setor.nome as nomeSetor, funcionario.data_nasc, funcionario.rg, funcionario.email, funcionario.ramal,  prontuarios.pendencias, prontuarios.ID_PRONTUARIO from prontuario as prontuarios inner join FUNCIONARIO as funcionario on funcionario.ID_MATRICULA = prontuarios.id_matricula inner join SETOR as setor on funcionario.ID_CENTRO_CUSTO = setor.ID_CENTRO_CUSTO inner join FUNCAO as funcao on funcionario.ID_FUNCAO = funcao.ID_FUNCAO  group by funcionario.nome, funcao.nome, setor.nome, funcionario.data_nasc, funcionario.rg, funcionario.email, funcionario.ramal,funcionario.id_matricula,  prontuarios.pendencias, prontuarios.ID_PRONTUARIO ");
		query.addScalar("nomeFuncionario");
		query.addScalar("nomeFuncao");
		query.addScalar("nomeSetor");
		query.addScalar("data_nasc");
		query.addScalar("email");
		query.addScalar("rg");
		query.addScalar("ramal");
		query.addScalar("id_matricula");
		query.addScalar("PENDENCIAS");
		query.addScalar("ID_PRONTUARIO");
		query.setResultTransformer(Transformers.aliasToBean(Prontuario.class));
		List<Prontuario> users = query.list();
		t.commit();
		return users;
	}
}
