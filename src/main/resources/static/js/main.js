var blogPostApi = Vue.resource('api/v1/posts{/id}');
Vue.component('blog-post', {
  props: ['post'],
  template: `
    <div class="blog-post">
      <h3>{{ post.title }}</h3>
      <div v-html="post.content"></div>
    </div>
  `
})

Vue.component('posts-list', {
  props: ['posts'],
  template:'<div><blog-post v-for="post in posts" :key="post.id" :post="post" /></div>'
})
var app = new Vue({
  el: '#app',
  template: '<posts-list :posts="posts" />',
  data: {
    posts: []
  },
  created: function() {
    blogPostApi.get().then(result =>
        result.json().then(data => data.content.forEach(post => this.posts.push(post)))
        )
  }
});