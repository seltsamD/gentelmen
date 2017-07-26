package com.gent.controller.rest;

import com.gent.dto.GoodDTO;
import com.gent.dto.MiniGoodDTO;
import com.gent.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by daria on 22.07.2017.
 */
@RestController
@ComponentScan("com.gent")
@RequestMapping("/api/good/")
public class GoodRestController {

    @Autowired
    private IGoodService goodService;

    @GetMapping
    public GoodDTO findById(@PathVariable int id) {
        return GoodDTO.convertToDTO(goodService.getGoodById(id));
    }

    @GetMapping("/random")
    public List<MiniGoodDTO> findForIndex() {
        return MiniGoodDTO.convertListToMiniDTO(goodService.getRandomGoods());
    }
}
