<template>
	<navigation v-show="isAuthenticated"></navigation>
	<div class="container">
			<!-- SPA content  -->
			<router-view></router-view>
	</div>
</template>

<script>
	import Navigation from './main/Navigation'
	import store from './store'

	export default {
		components: {
			Navigation
		},
		computed: {
			isAuthenticated () {
				return store.state.authenticated
			},
		}
	}
</script>

<style>
.gray-bg {
    background-color: #f3f3f4;
}
</style>
