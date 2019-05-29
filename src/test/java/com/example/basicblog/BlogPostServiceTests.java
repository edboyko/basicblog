package com.example.basicblog;

import com.example.basicblog.model.BlogPost;
import com.example.basicblog.service.BlogPostService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogPostServiceTests {

	@Autowired
	private BlogPostService blogPostService;

	BlogPost testPost;
	Long testPostId;

	@Before
	public void setUp() {
		testPost = new BlogPost();
		testPost.setTitle("TestTitle");
		testPost.setContent("TestContent.");
		testPostId = blogPostService.save(testPost).getId();
	}

	@After
	public void tearDown() {
		blogPostService.deletePosts(blogPostService.findAllPosts());
	}

	@Test
	public void testObjectCreated() {
		BlogPost foundPost = blogPostService.findPostById(testPostId);

		assertNotNull(foundPost);
		assertEquals(testPost.getTitle(), foundPost.getTitle());
		assertEquals(testPost.getContent(), foundPost.getContent());
		assertEquals(testPost.getCreatedDate(), foundPost.getCreatedDate());
		assertEquals(testPost.getCreatedDate(), foundPost.getLastEditedDate());
	}

	@Test
	public void testObjectDeleted() {
		assertEquals(1, blogPostService.findAllPosts().size());
		blogPostService.deletePost(testPostId);
		assertEquals(0, blogPostService.findAllPosts().size());
	}

	@Test
	public void testObjectModified() {
		String newTitle = "NewTestTitle";
		String newContent = "NewTestContent.";

		BlogPost foundPost = blogPostService.findPostById(testPostId);

		foundPost.setTitle(newTitle);
		foundPost.setContent(newContent);

		BlogPost editedPost = blogPostService.save(foundPost);

		assertEquals(editedPost.getTitle(), newTitle);
		assertEquals(editedPost.getContent(), newContent);
		assertNotEquals(editedPost.getCreatedDate(), editedPost.getLastEditedDate());
	}
}
