/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Paul Campbell
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.kemitix.naolo.presenter.rest.spring;

import net.kemitix.naolo.core.VeterinariansListAll;
import net.kemitix.naolo.entities.Veterinarian;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for Veterinarians.
 *
 * @author Paul Campbell (pcampbell@kemitix.net)
 */
@RestController
@RequestMapping("/vets")
public final class VeterinariansController {

    private static final VeterinariansListAll.Request LIST_ALL_REQUEST = new VeterinariansListAll.Request();

    private final VeterinariansListAll listAll;

    /**
     * Constructor.
     *
     * @param listAll the List All Veterinarians UseCase
     */
    public VeterinariansController(final VeterinariansListAll listAll) {
        this.listAll = listAll;
    }

    /**
     * List all Veterinarians endpoint.
     *
     * @return the respone
     */
    @GetMapping
    ResponseEntity<List<Veterinarian>> allVets() {
        return ResponseEntity.ok(listAll.invoke(LIST_ALL_REQUEST).getAllVeterinarians());
    }

}
