<template>
  <div class="animated fadeInRight">
		<div class="row">
			<div class="col-xs-10 col-xs-offset-1 m-b-10 text-right">
				We have found {{tweetsLength}} tweets matching your search.
			</div>
			<div class="col-xs-10 col-xs-offset-1">
        <div class="m-t-10">
          <div class="panel panel-default" v-for="tweet in tweets">
            <div class="panel-heading">@{{tweet.username}} - {{tweet.timestamp}}</div>
            <div class="panel-body">{{tweet.message}}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
	export default {
		computed: {
			tweetsLength () {
        if(this.tweets != null) {
					return this.tweets.length
				}
				return 0
			},
			tweets() {
				return this.$store.state.searchTweets
			}
		}
	}
</script>

<style scoped>
.m-b-10 {
	margin-bottom: 10px;
}
</style>
