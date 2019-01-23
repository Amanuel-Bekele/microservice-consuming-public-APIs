package com.altimetrik.music.music;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.altimetrik.music.domain.Artist;
import com.altimetrik.music.servcie.ArtistService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicApplicationTests {

	@Autowired
	ArtistService artistService;

	@Test
	public void testGetArtistInformation() {
		String name = "cher";
		String result = "Cher";

		Artist testCase = artistService.getArtistInfo(name);
		String artistName = "";

		if (testCase != null) {
			artistName = testCase.getName();
		}

		assertEquals(artistName, result);
	}
}
