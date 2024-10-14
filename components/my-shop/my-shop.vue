<template>
	<view class="shop-item">
		<!-- Left-side photo -->
		<view class="shop-left">
			<image :src="shop.shopSrc" class="shop-src"></image>
		</view>

		<!-- Right-side information -->
		<view class="shop-right">
			<!-- Shop name -->
			<view class="shop-name">
				{{shop.shopName}}
			</view>
			<!-- Shop introduction -->
			<view class="shop-summary">
				The owner said：{{shop.summary}}
			</view>
			<!-- Shop rating??? -->
			<view class="shop-stars">
				<uni-rate :readonly="true" :value="shop.stars" />
				<!-- active-color="#E67E22" -->
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		name: "my-shop",
		props: {
			shop: {
				type: Object,
				default: {}
			}
		},
		data() {
			return {

			};
		},
	}
</script>

<style lang="scss">
	.shop-item {
		// width: 750rpx;
		width: 100%;
		// Set the box model to border-box 
		box-sizing: border-box;
		display: flex;
		// padding: 10px 5px;
		// border-bottom: 1px solid #f0f0f0;
		
		.shop-left{
			margin-right: 5px;
			display: flex;
			justify-content: space-between;
			align-items: center;
			
			.shop-src {
				width: 100px;
				height: 100px;
				display: block;
			}
		}
		
		.shop-right{
			display: flex;
			flex-direction: column;
			justify-content: space-between;
			
			.shop-name{
				font-size: 18px;
				font-weight: bold;
				margin-bottom: 5rpx;
			}
			.shop-summary{
				font-size: 15px;
			}
			.shop-stars{
				padding: 5rpx;
			}
		}
	}
</style>

