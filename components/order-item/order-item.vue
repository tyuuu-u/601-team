<template>
	<view class="order-container">
		<!-- Shop name + Order status -->
		<!-- 		<view class="order-top">
			<view class="shopName">{{order.shopName}}</view>
			<view class="status">
				<text v-show="order.status===0">Preparing meal</text>
				<text v-show="order.status===1">Delivering</text>
				<text v-show="order.status===2">Completed</text>
				<text v-show="order.status===3">Order has been canceled</text>
			</view>
		</view> -->
		<uni-swipe-action>
			<uni-swipe-action-item :right-options="options" @click="swipeActionHandler">
				<van-cell-group>

					<van-cell :border="false" :title="order.shopName" :value="valueStatus" icon="shop-o" />

				</van-cell-group>
				<!-- Sliding dishes display -->
				<view class="order-body" @click="goToDetail">
					<scroll-view class="scroll-view_H" scroll-x="true" scroll-left="85%">
						<view v-for="(food,i) in order.foods" :key="i" class="scroll-view-item_H">
							<image :src="food.imgSrc" style="width: 120rpx;height: 120rpx; border-radius: 10px;">
							</image>
							<view>{{food.name}}*{{food.count}}</view>
						</view>
					</scroll-view>

					<view class="box-info">
						<view class="price">¥{{totalPrice}}</view>
						<view class="count">Total {{totalCount}} items</view>
					</view>
				</view>

			</uni-swipe-action-item>
		</uni-swipe-action>

		<!-- Show the comment button, only display when the order is completed -->
		<view class="order-bottom" @click="open">
			<view v-if="order.status===2&&order.commented===false">
				<button class="mini-btn" type="primary" plain="true" size="mini">Review</button>
			</view>
			<view v-if="order.status===2&&order.commented===true">
				<button class="mini-btn" type="primary" plain="true" size="mini" disabled>Reviewed</button>
			</view>
		</view>

		<!-- 		<uni-popup ref="popup" type="dialog">
			<uni-popup-dialog mode="input" :before-close="true" @close="close" @confirm="confirm" title="Review this shop"
				placeholder="Please enter review content"></uni-popup-dialog>
		</uni-popup> -->
		<van-dialog use-slot title="Review Shop" :show="showInput" show-cancel-button @close="onClose" @confirm="onConfirm">
			<van-cell-group>
				<van-field clearable="true" :value="content" @change="changeContent" placeholder="Please enter review content" />
			</van-cell-group>
		</van-dialog>

	</view>
</template>

<script>
	export default {
		name: "order-item",
		props: {
			order: {
				type: Object,
				default: {}
			}
		},
		computed: {
			valueStatus() {
				if (this.order.status === 0) return 'Preparing meal'
				if (this.order.status === 1) return 'Delivering'
				if (this.order.status === 2) return 'Completed'
				if (this.order.status === 3) return 'Order has been canceled'
			},
			totalPrice() {
				return this.order.foods.reduce((total, item) => total += item.count * item.price, 0)
			},
			totalCount() {
				return this.order.foods.reduce((total, item) => total += item.count, 0)
			},
		},
		data() {
			return {
				options: [{
					text: 'Delete', // Text to display
					style: {
						backgroundColor: '#ff0000' // Button background color
					}
				}],
				content: '',
				// popup dialog
				showInput: false
			};
		},
		methods: {
			onClose() {
				this.content = ''
				this.showInput = false
			},
			changeContent(e) {
				console.log(e, "onchange e")
				this.content = e.detail
			},
			onConfirm(e) {

				console.log("Comment received upon submission", this.content)
				this.order.status = 2
				const comment = {
					orderId: this.order.id,
					shopId: this.order.shopId,
					content: this.content,
				}
				console.log("Final comment object to submit", comment)

				this.$emit("submitComment", comment)
			},
			open() {
				// this.$refs.popup.open()
				this.showInput = true
			},
			close() {
				this.$refs.popup.close()
			},
			confirm(value) {
				// Value of input field
				console.log("Comment content", value)
				this.order.status = 2
				const comment = {
					orderId: this.order.id,
					shopId: this.order.shopId,
					content: value,
				}
				this.$emit("submitComment", comment)
				this.$refs.popup.close()
			},
			swipeActionHandler() {
				this.$emit("removeOrder", this.order.id)

				// this.removeFoodById(food.id)
			},
			goToDetail() {
				this.$emit("detail", this.order)
			}
		}
	}
</script>

<style lang="scss">
	.order-container {
		/* Width of the card */
		// width: 90%;
		/* Left margin, represents the distance from the leftmost side */
		// margin-left: 5%;
		/* Height of the card */
		height: 350rpx;
		/* Background color, can be changed */
		// background: rgb(226, 208, 208);
		/* radius is the roundness of the card's border
		    shadow is the shadow effect, the four values are horizontal shadow, vertical shadow, blur distance, shadow color.
		 */
		border-radius: 10rpx;
		box-shadow: 5px 5px 5px #cdcfcf;


		.order-top {
			display: flex;
			flex: 1;
			justify-content: space-between;

		}

		.order-body {
			display: flex;
			flex: 1;

			.box-info {
				padding-top: 30rpx;
				flex-direction: column;

				.price {
					color: red;
					font-weight: bold;
				}

				.count {
					padding-top: 10rpx;
				}
			}
		}

		.order-bottom {
			// display: flex;
			// flex: 1;
			// float: right;
			// padding-right: 20rpx;
			position: absolute;
			right: 0;
			padding-bottom: 10rpx;
			padding-right: 10rpx;

		}

		.scroll-view_H {
			white-space: nowrap;
			width: 85%;
		}

		.scroll-view-item_H {
			display: inline-block;
			padding-left: 30rpx;
			padding-top: 20rpx;
			width: 100px;
			height: 100%rpx;
			// line-height: 300rpx;
			// text-align: center;
			font-size: 25rpx;
		}
	}
</style>
