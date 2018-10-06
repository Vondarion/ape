package com.home.ape.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.home.ape.helpers.ApeServiceTest;
import com.home.ape.helpers.user.TestUserAdmin.ExecuteAsTestUserAdmin;
import com.home.ape.model.Item;

@ApeServiceTest
public class ItemServiceTest {
	@Autowired
	ItemService itemService;

	@Test
	@ExecuteAsTestUserAdmin
	void createItem() {
		Item item = Item.builder().state(
				"{\"query\" : {\"count\" : 1,\"created\" : \"2018-10-06T06:05:24Z\",\"lang\" : \"en-US\",\"results\" : {\"channel\" : {\"units\" : {\"distance\" : \"mi\",\"pressure\" : \"in\",\"speed\" : \"mph\",\"temperature\" : \"F\"},\"title\" : \"Yahoo! Weather - Mettmann, NW, DE\",\"link\" : \"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-675048/\",\"description\" : \"Yahoo! Weather for Mettmann, NW, DE\",\"language\" : \"en-us\",\"lastBuildDate\" : \"Sat, 06 Oct 2018 08:05 AM CEST\",\"ttl\" : \"60\",\"location\" : {\"city\" : \"Mettmann\",\"country\" : \"Germany\",\"region\" : \" NW\"},\"wind\" : {\"chill\" : \"54\",\"direction\" : \"130\",\"speed\" : \"11\"},\"atmosphere\" : {\"humidity\" : \"77\",\"pressure\" : \"996.0\",\"rising\" : \"0\",\"visibility\" : \"16.1\"},\"astronomy\" : {\"sunrise\" : \"7:42 am\",\"sunset\" : \"6:57 pm\"},\"image\" : {\"title\" : \"Yahoo! Weather\",\"width\" : \"142\",\"height\" : \"18\",\"link\" : \"http://weather.yahoo.com\",\"url\" : \"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\" : {\"title\" : \"Conditions for Mettmann, NW, DE at 07:00 AM CEST\",\"lat\" : \"51.251591\",\"long\" : \"6.97863\",\"link\" : \"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-675048/\",\"pubDate\" : \"Sat, 06 Oct 2018 07:00 AM CEST\",\"condition\" : {\"code\" : \"31\",\"date\" : \"Sat, 06 Oct 2018 07:00 AM CEST\",\"temp\" : \"55\",\"text\" : \"Clear\"},\"forecast\" : [ {\"code\" : \"34\",\"date\" : \"06 Oct 2018\",\"day\" : \"Sat\",\"high\" : \"74\",\"low\" : \"52\",\"text\" : \"Mostly Sunny\"},{\"code\" : \"12\",\"date\" : \"07 Oct 2018\",\"day\" : \"Sun\",\"high\" : \"57\",\"low\" : \"47\",\"text\" : \"Rain\"},{\"code\" : \"34\",\"date\" : \"08 Oct 2018\",\"day\" : \"Mon\",\"high\" : \"62\",\"low\" : \"41\",\"text\" : \"Mostly Sunny\"},{\"code\" : \"32\",\"date\" : \"09 Oct 2018\",\"day\" : \"Tue\",\"high\" : \"69\",\"low\" : \"44\",\"text\" : \"Sunny\"},{\"code\" : \"32\",\"date\" : \"10 Oct 2018\",\"day\" : \"Wed\",\"high\" : \"72\",\"low\" : \"50\",\"text\" : \"Sunny\"},{\"code\" : \"30\",\"date\" : \"11 Oct 2018\",\"day\" : \"Thu\",\"high\" : \"68\",\"low\" : \"54\",\"text\" : \"Partly Cloudy\"},{\"code\" : \"30\",\"date\" : \"12 Oct 2018\",\"day\" : \"Fri\",\"high\" : \"67\",\"low\" : \"55\",\"text\" : \"Partly Cloudy\"},{\"code\" : \"30\",\"date\" : \"13 Oct 2018\",\"day\" : \"Sat\",\"high\" : \"69\",\"low\" : \"52\",\"text\" : \"Partly Cloudy\"},{\"code\" : \"30\",\"date\" : \"14 Oct 2018\",\"day\" : \"Sun\",\"high\" : \"61\",\"low\" : \"48\",\"text\" : \"Partly Cloudy\"},{\"code\" : \"30\",\"date\" : \"15 Oct 2018\",\"day\" : \"Mon\",\"high\" : \"62\",\"low\" : \"51\",\"text\" : \"Partly Cloudy\"}],\"description\" : \"<![CDATA[<img src='http://l.yimg.com/a/i/us/we/52/31.gif'/>\n<BR />\n<b>Current Conditions:</b>\n<BR />Clear\n<BR />\n<BR />\n<b>Forecast:</b>\n<BR /> Sat - Mostly Sunny. High: 74Low: 52\n<BR /> Sun - Rain. High: 57Low: 47\n<BR /> Mon - Mostly Sunny. High: 62Low: 41\n<BR /> Tue - Sunny. High: 69Low: 44\n<BR /> Wed - Sunny. High: 72Low: 50\n<BR />\n<BR />\n<a href='http:// us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-675048/'>Full Forecast at Yahoo! Weather</a>\n<BR/>\n<BR/>\n<BR/>\n]]>\",\"guid\" : {\"isPermaLink\" : \"false\"}}}}}}")
				.build();
		item = itemService.create(item);

	}

}
