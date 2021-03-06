package sda.pl.web;

import sda.pl.domain.Cart;
import sda.pl.domain.Product;
import sda.pl.repository.CartRepository;
import sda.pl.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(name = "RemoveProductFromCartServlet", urlPatterns = "/removeProductFromCart")
public class RemoveProductFromCartServlet extends HttpServlet {

    //TODO get user id from session or cookie
    public static final long USER_ID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long productId = Long.parseLong(request.getParameter("productId"));

        Cart cart = CartRepository.findCartByUserId(USER_ID).get();
        CartRepository.deleteProductFromCart(cart, productId);
        CartRepository.saveOrUpdateCart(cart);

        PrintWriter writer = response.getWriter();
        writer.write("usunięto produkt z koszyka");
    }
}