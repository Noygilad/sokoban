package db;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class SokobanDBManager implements ScoreDataMapper {

	private SessionFactory factory;

	//C'tor
	public SokobanDBManager() {

		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Configuration config = new Configuration();
		config.configure();
		factory = config.buildSessionFactory();
	}

	//Save the score to DataBase
	@Override
	public void save(Score score) {
		Transaction tx = null;
		Session session = factory.openSession();
		try {
			tx = session.beginTransaction();
			session.save(score);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
	//search the desired score
	@Override
	public Score[] search(ScoreQuery query) {
		Session session = factory.openSession();
		Score[] results = null;
		Query q = session.createQuery(
				"From db.Score s WHERE (:levelName IS NULL OR s.levelName = :levelName) AND (:userName IS NULL OR s.userName = :userName) ORDER BY s."
						+ (query.getOrderBy()));

		q.setFirstResult(query.getPage()*50);
		q.setMaxResults(50);
		q.setParameter("levelName", query.getLevelName());
		q.setParameter("userName", query.getUserName());
		List list = q.list();
		results = new Score[list.size()];
		results = (Score[]) list.toArray(results);
		return results;
	}

}
