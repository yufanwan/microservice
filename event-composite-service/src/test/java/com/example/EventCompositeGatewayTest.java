// package com.example;

// import au.com.dius.pact.consumer.Pact;
// import au.com.dius.pact.consumer.PactProviderRule;
// import au.com.dius.pact.consumer.PactVerification;
// import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
// import au.com.dius.pact.model.PactFragment;
// import com.example.gateway.EventCompositeGateway;
// import com.example.model.Event;
// import org.apache.commons.collections.map.HashedMap;
// import org.junit.Before;
// import org.junit.Rule;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.cloud.client.ServiceInstance;
// import org.springframework.cloud.client.discovery.DiscoveryClient;
// import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
// import org.springframework.test.context.junit4.SpringRunner;

// import java.net.URI;
// import java.util.List;
// import java.util.Map;

// import static org.hamcrest.Matchers.is;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertThat;
// import static org.mockito.BDDMockito.given;
// import static org.mockito.Mockito.mock;

// /**
//  * Created by 42278 on 2017/4/16.
//  */
// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class EventCompositeGatewayTest {

//     private static String eventId = "57c811115d6fe2b86380d537";

//     @MockBean
//     private DiscoveryClient client;

//     @MockBean
//     private LoadBalancerClient loadBalancerClient;

//     @Autowired
//     private EventCompositeGateway eventCompositeGateway;

//     @Before
//     public void setUp() throws Exception{
//         ServiceInstance eventServerInstance = mock(ServiceInstance.class);
//         ServiceInstance reviewServerInstance = mock(ServiceInstance.class);
//         ServiceInstance recommendationServerInstance = mock(ServiceInstance.class);

//         given(eventServerInstance.getUri()).willReturn(new URI("http://localhost:9000"));
//         given(reviewServerInstance.getUri()).willReturn(new URI("http://localhost:9010"));
//         given(recommendationServerInstance.getUri()).willReturn(new URI("http://localhost:9020"));

//         given(loadBalancerClient.choose("EVENT")).willReturn(eventServerInstance);
//         given(loadBalancerClient.choose("REVIEW")).willReturn(reviewServerInstance);
//         given(loadBalancerClient.choose("RECOMMENDATION")).willReturn(recommendationServerInstance);
//     }

//     @Rule
//     public PactProviderRule eventServiceProvider = new PactProviderRule("EventService","localhost",9000,this);

//     @Rule
//     public PactProviderRule reviewServiceProvider = new PactProviderRule("ReviewService","localhost",9010,this);

//     @Rule
//     public PactProviderRule recommendationServiceProvider = new PactProviderRule("RecommendationService","localhost",9020,this);

//     @Pact(state = "whenEventDetailIsAvailable", provider = "EventService", consumer = "EventCompositeService")
//     public PactFragment createEventFragment(PactDslWithProvider builder) {
//         Map<String, String> headers = new HashedMap();
//         headers.put("Content-Type","application/hal+json;charset=UTF-8");

//         return builder
//                 .given("even detail is available")
//                 .uponReceiving("event detail request")
//                 .path("/events/"+eventId)
//                 .method("GET")
//                 .willRespondWith()
//                 .headers(headers)
//                 .status(200)
//                 .body("{\n" +
//                         "        \"name\": \"乐宝王国小精灵生日会\",\n" +
//                         "        \"numberLimit\": 50,\n" +
//                         "        \"mainPhoto\": \"http://cdn.kidsmeet.cn/Fv-5hE6njXOryyxDl1q1r3cfEf6t\",\n" +
//                         "        \"introduction\": \"生日会 ▎亲子互动 ▎免费送乐宝币\",\n" +
//                         "        \"startAt\": \"2016-08-27T07:00:00.000+0000\",\n" +
//                         "        \"endAt\": \"2016-08-27T09:00:00.000+0000\",\n" +
//                         "        \"_links\": {\n" +
//                         "          \"self\": {\n" +
//                         "            \"href\": \"http://localhost:9000/events/57c811115d6fe2b86380d537\"\n" +
//                         "          },\n" +
//                         "          \"event\": {\n" +
//                         "            \"href\": \"http://localhost:9000/events/57c811115d6fe2b86380d537\"\n" +
//                         "          }\n" +
//                         "        }\n" +
//                         "      }")
//                 .toFragment();
//     }

//     @Pact(provider = "ReviewService", consumer = "EventCompositeService")
//     public PactFragment createReviewFragment(PactDslWithProvider builder) {
//         Map<String, String> headers = new HashedMap();
//         headers.put("Content-Type","application/hal+json;charset=UTF-8");

//         return builder
//                 .given("reviews for event are available")
//                 .uponReceiving("request event reviews")
//                 .path("/reviews")
//                 .query("eventId="+eventId)
//                 .method("GET")
//                 .willRespondWith()
//                 .headers(headers)
//                 .status(200)
//                 .body("{\n" +
//                         "  \"_links\": {\n" +
//                         "    \"self\": {\n" +
//                         "      \"href\": \"http://localhost:9010/reviews?eventId=57c811115d6fe2b86380d537\"\n" +
//                         "    }\n" +
//                         "  },\n" +
//                         "  \"_embedded\": {\n" +
//                         "    \"reviews\": [\n" +
//                         "      {\n" +
//                         "        \"_links\": {\n" +
//                         "          \"self\": {\n" +
//                         "            \"href\": \"http://localhost:9010/reviews/57c811115d6fe2b86380d537\"\n" +
//                         "          }\n" +
//                         "        },\n" +
//                         "        \"eventId\": \"57c811115d6fe2b86380d537\",\n" +
//                         "        \"reviewId\": \"1\",\n" +
//                         "        \"author\": \"author 1\",\n" +
//                         "        \"subject\": \"subject 1\",\n" +
//                         "        \"content\": \"content 1\"\n" +
//                         "      },\n" +
//                         "      {\n" +
//                         "        \"_links\": {\n" +
//                         "          \"self\": {\n" +
//                         "            \"href\": \"http://localhost:9010/reviews/57c811115d6fe2b86380d537\"\n" +
//                         "          }\n" +
//                         "        },\n" +
//                         "        \"eventId\": \"57c811115d6fe2b86380d537\",\n" +
//                         "        \"reviewId\": \"3\",\n" +
//                         "        \"author\": \"author 3\",\n" +
//                         "        \"subject\": \"subject 3\",\n" +
//                         "        \"content\": \"content 3\"\n" +
//                         "      }\n" +
//                         "    ]\n" +
//                         "  }\n" +
//                         "}")
//                 .toFragment();
//     }

//     @Test
//     @PactVerification("EventService")
//     public void shouldReturnEventDetails() throws Exception {
//         Event event = eventCompositeGateway.getEvent(eventId);
//         assertEquals("乐宝王国小精灵生日会",event.getName());
//         assertEquals(50+"",event.getNumberLimit()+"");
//     }

//     @Test
//     @PactVerification("ReviewService")
//     public void shouldReturnReviews() throws Exception {
//         List<Map> reviews = eventCompositeGateway.getReviews(eventId);
//         assertThat(reviews.size(),is(2));
//         assertThat(reviews.get(0).get("eventId"),is("57c811115d6fe2b86380d537"));
//         assertThat(reviews.get(0).get("reviewId"),is("1"));
//         assertThat(reviews.get(0).get("author"),is("author 1"));
//     }


// }
