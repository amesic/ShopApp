package com.ajla.shop.seeders;

import com.ajla.shop.model.*;

import com.ajla.shop.repository.BrandRepository;
import com.ajla.shop.repository.UserRepository;
import com.ajla.shop.repository.RoleRepository;
import com.ajla.shop.repository.CategoryRepository;
import com.ajla.shop.repository.ProductRepository;
import com.ajla.shop.repository.CompanyRepository;

import com.stripe.exception.StripeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;


@Component
public class DatabaseSeeder {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;

    private Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    //dependency injection
    @Autowired
    public DatabaseSeeder(final UserRepository userRepository,
                          final RoleRepository roleRepository,
                          final CategoryRepository categoryRepository,
                          final BrandRepository brandRepository,
                          final CompanyRepository companyRepository,
                          final ProductRepository productRepository) {
        Objects.requireNonNull(brandRepository, "brandRepository must not be null.");
        Objects.requireNonNull(categoryRepository, "categoryRepository must not be null.");
        Objects.requireNonNull(userRepository, "userRepository must not be null.");
        Objects.requireNonNull(roleRepository, "roleRepository must not be null.");
        Objects.requireNonNull(companyRepository, "companyRepository must not be null.");
        Objects.requireNonNull(productRepository, "productRepository must not be null.");
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) throws StripeException {
        seedRoles();
        seedUsers();
        seedBrands();
        seedCategories();
        seedCompanies();
        seedProducts();
    }
    private void seedRoles() {
        Role role = roleRepository.findRoleById((long) 1);
        if (role == null) {
            Role r1 = new Role();
            r1.setName("user");
            Role r2 = new Role();
            r2.setName("admin");

            roleRepository.saveAll(Arrays.asList(r1, r2));
            logger.info("Role table seeded");

        } else {
            logger.trace("Role Seeding Not Required");
        }
    }

    private void seedUsers() {
        User user = userRepository.findUserById((long) 1);
        if (user == null) {
            final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            final Role userRole = roleRepository.findRoleById((long) 1);
            final Role adminRole = roleRepository.findRoleById((long) 2);


            User u = new User();
            u.setUserName("Huso Husic");
            u.setPassword(passwordEncoder.encode("12345678"));
            u.setEmail("huso@gmail.com");
            u.setRole(userRole);

            User u1 = new User();
            u1.setUserName("Mujo Mujic");
            u1.setPassword(passwordEncoder.encode("12345678"));
            u1.setEmail("mujo@gmail.com");
            u1.setRole(userRole);

            User u2 = new User();
            u2.setUserName("Fata Fatic");
            u2.setPassword(passwordEncoder.encode("12345678"));
            u2.setEmail("fata@gmail.com");
            u2.setRole(userRole);

            User u3 = new User();
            u3.setUserName("Suljo Suljic");
            u3.setPassword(passwordEncoder.encode("12345678"));
            u3.setEmail("suljo@gmail.com");
            u3.setRole(userRole);

            User u4 = new User();
            u4.setUserName("AdminCM");
            u4.setPassword(passwordEncoder.encode("12345678"));
            u4.setEmail("info@cm.ba");
            u4.setRole(adminRole);

            User u5 = new User();
            u5.setUserName("AdminDM");
            u5.setPassword(passwordEncoder.encode("12345678"));
            u5.setEmail("info@dm.ba");
            u5.setRole(adminRole);

            userRepository.saveAll(Arrays.asList(u, u1, u2, u3, u4, u5));
            logger.info("User table seeded");
        } else {
            logger.trace("User Seeding Not Required");
        }

    }

    private void seedBrands() {
        final Brand oneOfBrand = brandRepository.findBrandById((long) 1);
        if (oneOfBrand == null) {

            Brand b1 = new Brand();
            b1.setName("cosmetics_brands");
            Brand b2 = new Brand();
            b2.setName("Wet N Wild");
            Brand b3 = new Brand();
            b3.setName("L’Oréal Paris");
            Brand b4 = new Brand();
            b4.setName("Maybelline");
            Brand b5 = new Brand();
            b5.setName("Max Factor");
            b1.setBrands(Arrays.asList(b2, b3, b4, b5));

            Brand b7 = new Brand();
            b7.setName("haircare_brands");
            Brand b9 = new Brand();
            b9.setName("Dove");
            Brand b10 = new Brand();
            b10.setName("Garnier");
            Brand b11 = new Brand();
            b11.setName("Aveda");
            b7.setBrands(Arrays.asList(b9, b10, b11));


            Brand b8 = new Brand();
            b8.setName("skincare_brands");
            Brand b13 = new Brand();
            b13.setName("Nivea");
            Brand b14 = new Brand();
            b14.setName("Afrodita");
            b8.setBrands(Arrays.asList(b13, b14));

            brandRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5,b7, b8, b9, b10, b11,b13, b14));

            logger.info("Brand table seeded");
        }
        else {
            logger.trace("Brand Seeding Not Required");
        }

    }

    private void seedCategories() {
        final Category oneOfParentsCategories = categoryRepository.findCategoryById((long) 1);
        if (oneOfParentsCategories == null) {
            Brand cosmetics_brand = brandRepository.findBrandById((long) 1);
            Brand haircare_brand = brandRepository.findBrandById((long) 6);
            Brand skincare_brand = brandRepository.findBrandById((long) 10);

            Category c1 = new Category();
            c1.setName("Haircare");
            c1.setBrand(haircare_brand);
            Category c2 = new Category();
            c2.setName("Shampoo");
            Category c3 = new Category();
            c3.setName("Hair Conditioner");
            Category c4 = new Category();
            c4.setName("Hair Oil");
            Category c5 = new Category();
            c5.setName("Hair Accessories");
            c1.setSubcategories(Arrays.asList(c2, c3, c4, c5));

            Category c6 = new Category();
            c6.setName("Cosmetics");
            c6.setBrand(cosmetics_brand);
            Category c7 = new Category();
            c7.setName("Lipstick");
            Category c8 = new Category();
            c8.setName("Blush");
            Category c9 = new Category();
            c9.setName("Bronzer");
            Category c10 = new Category();
            c10.setName("Foundation");
            Category c11 = new Category();
            c11.setName("Nail Polish");
            Category c12 = new Category();
            c12.setName("Eyeliner");
            Category c13 = new Category();
            c13.setName("Mascara");
            c6.setSubcategories(Arrays.asList(c7, c8, c9, c10, c11, c12, c13));

            Category c16 = new Category();
            c16.setName("Skincare");
            c16.setBrand(skincare_brand);
            Category c17 = new Category();
            c17.setName("Eye Care");
            Category c18 = new Category();
            c18.setName("Cleanser");
            Category c19 = new Category();
            c19.setName("Milk");
            Category c20 = new Category();
            c20.setName("Oils");
            c16.setSubcategories(Arrays.asList(c17, c18, c19, c20));

            categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7,
                    c8, c9, c10, c11, c12, c13, c16, c17, c18, c19, c20));
            logger.info("Category table seeded");
        }
        else {
            logger.trace("Category Seeding Not Required");
        }
    }

    private void seedCompanies() {
        final Company oneOfCompanies = companyRepository.findCompanyById((long) 1);
        if (oneOfCompanies == null) {
            Company c1 = new Company();
            c1.setName("Cosmetic Market (CM)");

            Company c2 = new Company();
            c2.setName("Drogerie Markt (DM)");

            companyRepository.saveAll(Arrays.asList(c1, c2));

            logger.info("Company table seeded");
        }
        else {
            logger.trace("Company Seeding Not Required");
        }


    }

    private void seedProducts() {
        final Product oneOfProducts = productRepository.findProductById((long) 1);
        if (oneOfProducts == null) {
            Brand maxFactor = brandRepository.findBrandById((long) 5);
            Brand nivea = brandRepository.findBrandById((long) 13);

            Company cm = companyRepository.findCompanyById((long) 1);
            Company dm = companyRepository.findCompanyById((long) 2);

            Category cosmetics = categoryRepository.findCategoryById((long) 1);
            Category sub_cosmetics_foundation = categoryRepository.findCategoryById((long) 11);

            Category skincare = categoryRepository.findCategoryById((long) 16);
            Category sub_skincare_other = categoryRepository.findCategoryById((long) 21);


            Product p1 = new Product();
            p1.setName("Radiant Lift Concealer");
            p1.setBrand(maxFactor);
            p1.setCategory(cosmetics);
            p1.setSubcategory(sub_cosmetics_foundation);
            p1.setCompany(cm);
            p1.setDescription("Radiant Lift Concealer sljedeći je proizvod iz nove linije, a koji omogućuje trenutno posvjetljivanje i blistavost. Ovaj tekući korektor lako se nanosi i razmazuje zahvaljujući mekom spužvastom aplikatoru.");
            p1.setImage("https://www.womeninadria.com/wp-content/uploads/2018/12/max-factor-puder.jpg");
            p1.setPrice((double) 20);
            p1.setDate_published(LocalDateTime.now());

            Product p2 = new Product();
            p2.setName("Radiant Lift Concealer");
            p2.setBrand(maxFactor);
            p2.setCategory(cosmetics);
            p2.setSubcategory(sub_cosmetics_foundation);
            p2.setCompany(dm);
            p2.setDescription("Radiant Lift Concealer sljedeći je proizvod iz nove linije, a koji omogućuje trenutno posvjetljivanje i blistavost. Ovaj tekući korektor lako se nanosi i razmazuje zahvaljujući mekom spužvastom aplikatoru.");
            p2.setImage("https://www.womeninadria.com/wp-content/uploads/2018/12/max-factor-puder.jpg");
            p2.setPrice((double) 25);
            p2.setDate_published(LocalDateTime.now());

            Product p3 = new Product();
            p3.setName("Nivea krema");
            p3.setBrand(nivea);
            p3.setCategory(skincare);
            p3.setSubcategory(sub_skincare_other);
            p3.setCompany(dm);
            p3.setDescription("NIVEA Creme nenadmašna je hidratantna krema za sve tipove kože, za djecu kao i za odrasle tijekom svih godišnjih doba. NIVEA Creme sadrži koži srodan Eucerit®, jedinstveni hidratantni sastojak koji koži pruža svu potrebnu vlažnost.");
            p3.setImage("https://www.nivea.hr/~/images/media-center-items/5/9/7-149992-2.jpg?mw=804&mh=1000");
            p3.setPrice((double) 15);
            p3.setDate_published(LocalDateTime.now());

            Product p4 = new Product();
            p4.setName("Nivea krema");
            p4.setBrand(nivea);
            p4.setCategory(skincare);
            p4.setSubcategory(sub_skincare_other);
            p4.setCompany(cm);
            p4.setDescription("NIVEA Creme nenadmašna je hidratantna krema za sve tipove kože, za djecu kao i za odrasle tijekom svih godišnjih doba. NIVEA Creme sadrži koži srodan Eucerit®, jedinstveni hidratantni sastojak koji koži pruža svu potrebnu vlažnost.");
            p4.setImage("https://www.nivea.hr/~/images/media-center-items/5/9/7-149992-2.jpg?mw=804&mh=1000");
            p4.setPrice((double) 15);
            p4.setDate_published(LocalDateTime.now());

            productRepository.saveAll(Arrays.asList(p1, p2, p3 , p4));

            logger.info("Product table seeded");
        } else {
            logger.trace("Product Seeding Not Required");
        }


    }
}
