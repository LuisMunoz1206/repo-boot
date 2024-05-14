package gm.practica.services;

import gm.practica.DTOs.PostDTO;
import gm.practica.entities.Post;
import gm.practica.entities.Product;
import gm.practica.entities.User;
import gm.practica.exceptions.NotFoundException;
import gm.practica.repository.IPostRepository;
import gm.practica.repository.IProductRepository;
import gm.practica.repository.IUsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IUsersRepository usersRepository;

    private ModelMapper mapper = new ModelMapper();

   public PostDTO createPost(PostDTO postDTO) {
    if (postDTO.getUserId() == null) {
        throw new NotFoundException("userId no puede ser null");
    }

    User user = usersRepository.findById(postDTO.getUserId())
        .orElseThrow(() -> new NotFoundException("no se encontro el usuario"));

    Product product = mapper.map(postDTO.getProduct(), Product.class);
    product.setUser(user);
    productRepository.save(product);

    Post post = mapper.map(postDTO, Post.class);
    post.setProduct(product);

    Post savedPost = postRepository.save(post);
    return mapper.map(savedPost, PostDTO.class);
}

    public List<PostDTO> getProductsByUser(Long userId){
       List<Product> products = productRepository.findByUserId(userId);
         if(products.isEmpty()){
              throw new NotFoundException("No hay productos para el usuario con id " + userId);
         }
            return products.stream()
                    .map(product -> mapper.map(product, PostDTO.class))
                    .toList();
    }


}