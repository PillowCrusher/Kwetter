<template>
	<navigation v-if="isAuthenticated"></navigation>
	<div class="container container-app">
			<!-- SPA content  -->
			<router-view></router-view>
	</div>
</template>

<script>
	import Navigation from './main/Navigation'

	export default {
		components: {
			Navigation
		},
		computed: {
			isAuthenticated () {
				return this.$store.state.authenticated
			},
		}
	}
</script>

<style>
.gray-bg {
    background-color: #f3f3f4;
}

.container-app {
	min-width: 500px;
	width: auto !important;
}
</style>
