package com.lijian.webservice;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 * JAX-RS: Java API for RESTful Web Services
 */
//Path 标注的值是一个相对的 URI 路径，用于对资源进行定位，路径中可以包含任意的正则表达式以匹配资源

//方法参数的标注包括：@PathParam、@MatrixParam、@QueryParam、@FormParam、@HeaderParam、@CookieParam、@DefaultValue 和 @Encoded

//    Request、UriInfo、HttpHeaders、Providers、SecurityContext
//    HttpServletRequest、HttpServletResponse、ServletContext、ServletConfig
@Path("/")
public class BookkeepingService {
    Logger logger = LoggerFactory.getLogger(BookkeepingService.class);

    public static final List<Person> personList = Stream.of(new Person(20, "lijian"), new Person(21, "GG")).collect(Collectors.toList());


    //    public static final ImmutableList<Color> GOOGLE_COLORS
//   *       = new ImmutableList.Builder<Color>()
//           *           .addAll(WEBSAFE_COLORS)
//   *           .add(new Color(0, 191, 255))
//            *           .build();}
    @Path("/person/")
    @POST
    @Consumes("application/json")
    public Response createPerson(Person person) {
        logger.info("create person:" + person.toString());
        return Response.ok().build();
    }

    @Path("/person/")
    @PUT
    @Consumes("application/json")
    public Response updatePerson(Person person) {
        logger.info("update person:" + person.toString());
        return Response.ok().build();
    }

    @Path("/person/{id:\\d+}/")
    @DELETE
//    它用于将 @Path 中的模板变量映射到方法参数，模板变量支持使用正则表达式，变量名与正则表达式之间用分号分隔。
    public Response deletePerson(@PathParam("id")
                                         int id) {
        logger.info("delete person,person'id:" + id);
        return Response.ok().build();

    }

    @Path("/person/{id:\\d+}/")
    @GET
    @Produces("application/json")
    public Person readPerson(@PathParam("id")
                                     int id) {
        logger.info("get person,person'id" + id);
        return personList.stream().filter(x -> x.getAge() == id).findFirst().get();
    }

    @Path("/persons/")
    @GET
    @Produces("application/json")
    public Person[] readAllPersons() {
        logger.info("get person list" + personList);
        return (Person[]) personList.toArray();
    }

    @Path("/person/{name}/")
    @GET
    @Produces("application/json")
    public Person readPersonByName(@PathParam("name")
                                           String name) {
        logger.info("get person by name,name:" + name);
        return personList.stream().filter(x -> x.getName().equals(name)).findFirst().get();
    }
}