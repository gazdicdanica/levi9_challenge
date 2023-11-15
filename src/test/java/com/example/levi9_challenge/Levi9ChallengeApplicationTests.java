package com.example.levi9_challenge;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class Levi9ChallengeApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void publicTestCase_happyFlow() throws Exception {
		String playerName = "Sifiso Abdalla";

		MvcResult mvcResult = mockMvc.perform(get("/stats/player/{playerFullName}", playerName)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		// Then
		String expectedResponseBody = """
				{
				 "playerName": "Sifiso Abdalla",
				 "gamesPlayed": 3,
				 "traditional": {
				  "freeThrows": {
				   "attempts": 4.7,
				   "made": 3.3,
				   "shootingPercentage": 71.4
				  },
				  "twoPoints": {
				   "attempts": 4.7,
				   "made": 3.0,
				   "shootingPercentage": 64.3
				  },
				  "threePoints": {
				   "attempts": 6.3,
				   "made": 1.0,
				   "shootingPercentage": 15.8
				  },
				  "points": 12.3,
				  "rebounds": 5.7,
				  "blocks": 1.7,
				  "assists": 0.7,
				  "steals": 1.0,
				  "turnovers": 1.3
				 },
				 "advanced": {
				  "valorization": 11.7,
				  "effectiveFieldGoalPercentage": 40.9,
				  "trueShootingPercentage": 46.7,
				  "hollingerAssistRatio": 4.4
				 }
				}""";

		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		JSONAssert.assertEquals(expectedResponseBody, actualResponseBody, false);
	}

	@Test
	void publicTestCase_notExists() throws Exception {
		String playerName = "Danica Gazdic";

		MvcResult mvcResult = mockMvc.perform(get("/stats/player/{playerFullName}", playerName)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(404))
				.andReturn();
		String expectedResponseBody = """
				{
				 "explanation": "Player not found"
				 }""";
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		JSONAssert.assertEquals(expectedResponseBody, actualResponseBody, false);
	}

}
