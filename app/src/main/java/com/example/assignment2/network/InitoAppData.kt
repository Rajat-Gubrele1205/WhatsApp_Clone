import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductData(
    @SerializedName("product_id") val productId: String,
    @SerializedName("checkout_url") val checkoutUrl: String,
    val title: String,
    val price: String,
    val description: String,
    @SerializedName("shipping_info") val shippingInfo: String,
    @SerializedName("discounted_price") val discountedPrice: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("button_text") val buttonText: String,
    @SerializedName("out_of_stock") val outOfStock: Boolean
)

@Serializable
data class ProductsData(
    val monitor: List<ProductData>,
    @SerializedName("monitor-pro") val monitorPro: List<ProductData>,
    @SerializedName("replacement-monitor") val replacementMonitor: List<ProductData>,
    @SerializedName("transmissive-strip") val transmissiveStrip: List<ProductData>,
    @SerializedName("reflective-strip") val reflectiveStrip: List<ProductData>,
    @SerializedName("reflective_3T_strip") val reflective3TStrip: List<ProductData>,
    val clip: List<ProductData>
)

@Serializable
data class Information(
    @SerializedName("shop_message") val shopMessage: String
)

@Serializable
data class ProductResponseData(
    val products: ProductsData,
    val information: Information
)
