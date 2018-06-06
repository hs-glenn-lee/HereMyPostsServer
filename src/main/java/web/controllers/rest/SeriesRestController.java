package web.controllers.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Account;
import web.model.jpa.entities.Series;
import web.model.service.SeriesService;
import web.model.service.sign.SignService;

@Controller
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SeriesRestController {
	
	@Autowired
	SeriesService seriesService;
	
	@Autowired
	SignService signService;
	

	@RequestMapping(value="/series/create", method=RequestMethod.PUT)
	public Series createNewSeries(@RequestBody Series series, HttpServletRequest req) throws NotSignedInException {
		Account me = signService.getSign(req.getSession()).getAccount();
		series.setOwner(me);
		series = seriesService.createNewSeries(series);
		return series;
	}
	
	@RequestMapping(value="/{username}/all-series", method=RequestMethod.GET)
	public List<Series> getMySeries(@PathVariable String username, HttpServletRequest req) {
		List<Series> ret = seriesService.getSeriesByUsername(username);
		return ret;
	}
	
	
}
