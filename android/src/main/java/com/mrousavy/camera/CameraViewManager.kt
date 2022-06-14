package com.mrousavy.camera

import android.widget.RelativeLayout
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

@Suppress("unused")
class CameraViewManager(reactContext: ReactApplicationContext) : ViewGroupManager<RelativeLayout>() {

  public override fun createViewInstance(context: ThemedReactContext): RelativeLayout {
    val layout = RelativeLayout(context)

    createCameraView(context, layout)

    return layout
  }

  override fun onAfterUpdateTransaction(parent: RelativeLayout) {
    super.onAfterUpdateTransaction(parent)
    val cameraView = cameraViews[parent]
    val changedProps = cameraViewTransactions[cameraView] ?: ArrayList()
    cameraView?.update(changedProps)
    cameraViewTransactions.remove(cameraView)
  }

  override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Any>? {
    return MapBuilder.builder<String, Any>()
      .put("cameraViewReady", MapBuilder.of("registrationName", "onViewReady"))
      .put("cameraInitialized", MapBuilder.of("registrationName", "onInitialized"))
      .put("cameraError", MapBuilder.of("registrationName", "onError"))
      .put("cameraPerformanceSuggestionAvailable", MapBuilder.of("registrationName", "onFrameProcessorPerformanceSuggestionAvailable"))
      .build()
  }

  override fun getName(): String {
    return TAG
  }

  @ReactProp(name = "cameraId")
  fun setCameraId(parent: RelativeLayout, cameraId: String) {
    val cameraView = cameraViews[parent]
    if (cameraView?.cameraId != cameraId)
      addChangedPropToTransaction(cameraView, "cameraId")
    cameraView?.cameraId = cameraId
  }

  @ReactProp(name = "photo")
  fun setPhoto(parent: RelativeLayout, photo: Boolean?) {
    val cameraView = cameraViews[parent]
    if (cameraView?.photo != photo)
      addChangedPropToTransaction(cameraView, "photo")
    cameraView?.photo = photo
  }

  @ReactProp(name = "video")
  fun setVideo(parent: RelativeLayout, video: Boolean?) {
    val cameraView = cameraViews[parent]
    if (cameraView?.video != video)
      addChangedPropToTransaction(cameraView, "video")
    cameraView?.video = video
  }

  @ReactProp(name = "audio")
  fun setAudio(parent: RelativeLayout, audio: Boolean?) {
    val cameraView = cameraViews[parent]
    if (cameraView?.audio != audio)
      addChangedPropToTransaction(cameraView, "audio")
    cameraView?.audio = audio
  }

  @ReactProp(name = "enableFrameProcessor")
  fun setEnableFrameProcessor(parent: RelativeLayout, enableFrameProcessor: Boolean) {
    val cameraView = cameraViews[parent]
    if (cameraView?.enableFrameProcessor != enableFrameProcessor)
      addChangedPropToTransaction(cameraView, "enableFrameProcessor")
    cameraView?.enableFrameProcessor = enableFrameProcessor
  }

  @ReactProp(name = "enableDepthData")
  fun setEnableDepthData(parent: RelativeLayout, enableDepthData: Boolean) {
    val cameraView = cameraViews[parent]
    if (cameraView?.enableDepthData != enableDepthData)
      addChangedPropToTransaction(cameraView, "enableDepthData")
    cameraView?.enableDepthData = enableDepthData
  }

  @ReactProp(name = "enableHighQualityPhotos")
  fun setEnableHighQualityPhotos(parent: RelativeLayout, enableHighQualityPhotos: Boolean?) {
    val cameraView = cameraViews[parent]
    if (cameraView?.enableHighQualityPhotos != enableHighQualityPhotos)
      addChangedPropToTransaction(cameraView, "enableHighQualityPhotos")
    cameraView?.enableHighQualityPhotos = enableHighQualityPhotos
  }

  @ReactProp(name = "enablePortraitEffectsMatteDelivery")
  fun setEnablePortraitEffectsMatteDelivery(parent: RelativeLayout, enablePortraitEffectsMatteDelivery: Boolean) {
    val cameraView = cameraViews[parent]
    if (cameraView?.enablePortraitEffectsMatteDelivery != enablePortraitEffectsMatteDelivery)
      addChangedPropToTransaction(cameraView, "enablePortraitEffectsMatteDelivery")
    cameraView?.enablePortraitEffectsMatteDelivery = enablePortraitEffectsMatteDelivery
  }

  @ReactProp(name = "format")
  fun setFormat(parent: RelativeLayout, format: ReadableMap?) {
    val cameraView = cameraViews[parent]
    if (cameraView?.format != format)
      addChangedPropToTransaction(cameraView, "format")
    cameraView?.format = format
  }

  // TODO: Change when TurboModules release.
  // We're treating -1 as "null" here, because when I make the fps parameter
  // of type "Int?" the react bridge throws an error.
  @ReactProp(name = "fps", defaultInt = -1)
  fun setFps(parent: RelativeLayout, fps: Int) {
    val cameraView = cameraViews[parent]
    if (cameraView?.fps != fps)
      addChangedPropToTransaction(cameraView, "fps")
    cameraView?.fps = if (fps > 0) fps else null
  }

  @ReactProp(name = "frameProcessorFps", defaultDouble = 1.0)
  fun setFrameProcessorFps(parent: RelativeLayout, frameProcessorFps: Double) {
    val cameraView = cameraViews[parent]
    if (cameraView?.frameProcessorFps != frameProcessorFps)
      addChangedPropToTransaction(cameraView, "frameProcessorFps")
    cameraView?.frameProcessorFps = frameProcessorFps
  }

  @ReactProp(name = "hdr")
  fun setHdr(parent: RelativeLayout, hdr: Boolean?) {
    val cameraView = cameraViews[parent]
    if (cameraView?.hdr != hdr)
      addChangedPropToTransaction(cameraView, "hdr")
    cameraView?.hdr = hdr
  }

  @ReactProp(name = "lowLightBoost")
  fun setLowLightBoost(parent: RelativeLayout, lowLightBoost: Boolean?) {
    val cameraView = cameraViews[parent]
    if (cameraView?.lowLightBoost != lowLightBoost)
      addChangedPropToTransaction(cameraView, "lowLightBoost")
    cameraView?.lowLightBoost = lowLightBoost
  }

  @ReactProp(name = "colorSpace")
  fun setColorSpace(parent: RelativeLayout, colorSpace: String?) {
    val cameraView = cameraViews[parent]
    if (cameraView?.colorSpace != colorSpace)
      addChangedPropToTransaction(cameraView, "colorSpace")
    cameraView?.colorSpace = colorSpace
  }

  @ReactProp(name = "isActive")
  fun setIsActive(parent: RelativeLayout, isActive: Boolean) {
    val cameraView = cameraViews[parent]
    if (cameraView?.isActive != isActive)
      addChangedPropToTransaction(cameraView, "isActive")
    cameraView?.isActive = isActive
  }

  @ReactProp(name = "torch")
  fun setTorch(parent: RelativeLayout, torch: String) {
    val cameraView = cameraViews[parent]
    if (cameraView?.torch != torch)
      addChangedPropToTransaction(cameraView, "torch")
    cameraView?.torch = torch
  }

  @ReactProp(name = "zoom")
  fun setZoom(parent: RelativeLayout, zoom: Double) {
    val cameraView = cameraViews[parent]
    val zoomFloat = zoom.toFloat()
    if (cameraView?.zoom != zoomFloat)
      addChangedPropToTransaction(cameraView, "zoom")
    cameraView?.zoom = zoomFloat
  }

  @ReactProp(name = "enableZoomGesture")
  fun setEnableZoomGesture(parent: RelativeLayout, enableZoomGesture: Boolean) {
    val cameraView = cameraViews[parent]
    if (cameraView?.enableZoomGesture != enableZoomGesture)
      addChangedPropToTransaction(cameraView, "enableZoomGesture")
    cameraView?.enableZoomGesture = enableZoomGesture
  }

  @ReactProp(name = "orientation")
  fun setOrientation(parent: RelativeLayout, orientation: String) {
    val cameraView = cameraViews[parent]
    if (cameraView?.orientation != orientation)
      addChangedPropToTransaction(cameraView, "orientation")
    cameraView?.orientation = orientation
  }

  companion object {
    const val TAG = "CameraView"

    val cameraViewTransactions: HashMap<CameraView, ArrayList<String>> = HashMap()
    val cameraViews: HashMap<RelativeLayout, CameraView> = HashMap()

    private fun addChangedPropToTransaction(view: CameraView?, changedProp: String) {
      if (view == null) {
        return;
      }

      if (cameraViewTransactions[view] == null) {
        cameraViewTransactions[view] = ArrayList()
      }
      cameraViewTransactions[view]!!.add(changedProp)
    }
  }

  val matchParentLayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
  private fun createCameraView(context: ReactContext, parent: RelativeLayout) {
    if (cameraViews[parent] == null) {
      val cameraViewModule = context.getNativeModule(CameraViewModule::class.java)!!
      val cameraView = CameraView(context, cameraViewModule.frameProcessorThread)

      cameraView.layoutParams = matchParentLayoutParams
      parent.addView(cameraView)

      cameraViews[parent] = cameraView
    }
  }
}
