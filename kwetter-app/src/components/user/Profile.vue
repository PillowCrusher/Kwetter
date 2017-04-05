<template>
  <div class="animated fadeInRight">
    <div class="row">
      <div class="col-md-6 col-lg-6">
        <div class="row">
          <div class="col-lg-12">
            <div class="panel panel-default">
    					<div class="panel-heading">Profile</div>
    					<div class="panel-body">
                <div class="row">
                  <div class="col-xs-4">
                    <img alt="140x140" data-src="holder.js/140x140" class="img-rounded" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgdmlld0JveD0iMCAwIDE0MCAxNDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzE0MHgxNDAKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNWIwZmMzMjQxNiB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1YjBmYzMyNDE2Ij48cmVjdCB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjQ0LjA1NDY4NzUiIHk9Ijc0LjUiPjE0MHgxNDA8L3RleHQ+PC9nPjwvZz48L3N2Zz4=" data-holder-rendered="true" style="width: 140px; height: 140px;">
                  </div>
                  <div class="col-xs-8">
                    <h5>Tweet amount: {{getTweetsLength}}</h5>
                    <h5>following: {{getFollowingLength}}</h5>
                    <h5>follower: {{getFollowerLength}}</h5>
                    <h5>last tweet: {{getLastTweet}}</h5>
                  </div>
                </div>
    					</div>
    				</div>
          </div>
          <div class="col-lg-12">
            <div class="panel panel-default">
              <div class="panel-heading">Tweets</div>
              <div class="panel-body">
                <div class="m-t-10">
                  <div class="panel panel-default" v-for="tweet in t_tweets">
                    <div class="panel-heading">@{{tweet.username}} - {{tweet.timestamp}}</div>
                    <div class="panel-body">{{tweet.message}}</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-6 col-lg-6">
        <div class="row">
          <div class="col-lg-12">
            <div class="panel panel-default">
              <div class="panel-heading">Details</div>
              <div class="panel-body">
                <div class="col-xs-8">
                  <h5>Email: {{currentUser.email}}</h5>
                  <h5>Bio: {{currentUser.bio}}</h5>
                  <h5>Location: {{currentUser.location}}</h5>
                  <h5>Website: {{currentUser.websiteUrl}}</h5>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="panel panel-default">
              <div class="panel-heading">Following</div>
              <div class="panel-body">
                <div v-for="following in currentUser.following">
                  <a @click="viewProfile(following.id)">{{following.username}}</a>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12">
            <div class="panel panel-default">
              <div class="panel-heading">Followers</div>
              <div class="panel-body">
                <div v-for="follower in currentUser.followers">
                  <a @click="viewProfile(follower.id)">{{follower.username}}</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

  export default {
    data () {
      return {
        //User profile data
        currentUser: {},

        // Timeline tweets
        t_tweets: null
      }
    },
    ready () {
      this.getUser(this.$route.params.id)
      this.getTweets(this.$route.params.id)
    },
    computed: {
      getFollowingLength () {
        if(this.currentUser.following != null) {
          return this.currentUser.following.length
        }
        return 0
      },
      getFollowerLength () {
        if(this.currentUser.follower != null) {
          return this.currentUser.follower.length
        }
        return 0
      },
      getTweetsLength () {
        if(this.t_tweets) {
          return this.t_tweets.length
        }
        return 0
      },
      getLastTweet () {
        if(this.t_tweets) {
          return this.t_tweets[0].message
        }
        return "No tweet created yet!"
      }
    },
    methods: {
      getTweets (id) {
        this.$http.get( this.$apiurl + '/user/'+ id + '/tweet').then(response => {
          this.t_tweets = response.data
        }, response => {
          this.showErrorToastr(response.data.message)
        })
      },
      getUser (id) {
        this.$http.get( this.$apiurl + '/user/' + id).then(response => {
          this.currentUser = response.data
        }, response => {
          this.showErrorToastr(response.data.message)
        })
      },
      viewProfile (id) {
        this.$router.go('/User/' + id + '/Profile')
      }
    }
  }
</script>

<style scoped>
.m-t-10 {
	margin-left: 5px;
	margin-top: 10px;
}

.m-t-30 {
	margin-top: 30px;
}
</style>
