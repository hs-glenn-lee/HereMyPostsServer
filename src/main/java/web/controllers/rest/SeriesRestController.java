package web.controllers.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import web.controllers.rest.parameters.PageParameter;
import web.exceptions.NotSignedInException;
import web.model.jpa.entities.Account;
import web.model.jpa.entities.Series;
import web.model.service.SeriesService;
import web.model.service.sign.SignService;

@RestController
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
		List<Series> ret = new ArrayList<>(); // init as empty list
		ret = seriesService.getSeriesByUsername(username);
		return ret;
	}
	
	@RequestMapping(value="/series/all-series-by-page", method=RequestMethod.POST)
	public List<Series> getMySeriesByPage(HttpServletRequest req, @RequestBody PageParameter pageParam) throws NotSignedInException {
		PageRequest pr = new PageRequest(pageParam.getPage(), pageParam.getSize());
		List<Series> seriesList = new ArrayList<>();
		seriesList = seriesService.findMySeriesByPage(req.getSession(), pr);
		return seriesList;
	}
}
