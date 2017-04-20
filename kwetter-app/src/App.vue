<template>
	<navigation></navigation>
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
