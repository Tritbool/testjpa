package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;

import domain.Person;

@WebServlet(name = "userslist", urlPatterns = { "/UsersList" })

public class UsersList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();


		
		BasicConfigurator.configure();

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();

		String s = "SELECT p FROM Person p";

		Query q = manager.createQuery(s, Person.class);
		
		List<Person> res = q.getResultList();
		StringBuffer sb = new StringBuffer();

		
		sb.append("<HTML>\n<BODY>\n" + "<H1>Liste des PD</H1>\n" + "<UL>\n");
		
		for(int i=0; i<res.size();i++){
			sb.append(" <LI>Nom: "+ res.get(i).getName() + "\n" + " <LI>Age: " + res.get(i).getAge() + "\n<LI>");
		}

		sb.append("</UL>\n" + "</BODY></HTML>");
		out.println(sb.toString());
		manager.close();
		factory.close();
	}

}
