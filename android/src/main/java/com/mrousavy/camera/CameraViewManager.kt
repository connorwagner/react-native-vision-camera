package com.mrousavy.camera

import android.view.View
import android.widget.RelativeLayout
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.views.view.ReactViewGroup

@Suppress("unused")
class CameraViewManager(reactContext: ReactApplicationContext) : ViewGroupManager<RelativeLayout>() {

  private var viewGroup: RelativeLayout? = null
  private var cameraView: CameraView? = null
  private var reactViewGroup: ReactViewGroup? = null

  override fun addView(parent: RelativeLayout?, child: View?, index: Int) {
    reactViewGroup?.addView(child, index)
  }

  override fun addViews(parent: RelativeLayout?, views: MutableList<View>?) {
    views?.forEach { addView(parent, it, 0) }
  }

  private var isSetup = false
  public override fun createViewInstance(context: ThemedReactContext): RelativeLayout {
    if (!isSetup) {
      isSetup = true

      val layoutParams = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.MATCH_PARENT,
        RelativeLayout.LayoutParams.MATCH_PARENT
      )

      cameraView = CameraView(context, context.getNativeModule(CameraViewModule::class.java)!!.frameProcessorThread)
      cameraView!!.layoutParams = layoutParams

      reactViewGroup = ReactViewGroup(context)
      reactViewGroup!!.layoutParams = layoutParams

      viewGroup = RelativeLayout(context)
      viewGroup!!.addView(cameraView)
      viewGroup!!.addView(reactViewGroup)
    }

    return viewGroup!!
  }

  override fun onAfterUpdateTransaction(view: RelativeLayout) {
    super.onAfterUpdateTransaction(view)

    val changedProps = cameraViewTransactions[view] ?: ArrayList()
    cameraView?.update(changedProps)
    cameraViewTransactions.remove(view)
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
  fun setCameraId(view: RelativeLayout, cameraId: String) {
    if (cameraView?.cameraId != cameraId)
      addChangedPropToTransaction(view, "cameraId")
    cameraView?.cameraId = cameraId
  }

  @ReactProp(name = "photo")
  fun setPhoto(view: RelativeLayout, photo: Boolean?) {
    if (cameraView?.photo != photo)
      addChangedPropToTransaction(view, "photo")
    cameraView?.photo = photo
  }

  @ReactProp(name = "video")
  fun setVideo(view: RelativeLayout, video: Boolean?) {
    if (cameraView?.video != video)
      addChangedPropToTransaction(view, "video")
    cameraView?.video = video
  }

  @ReactProp(name = "audio")
  fun setAudio(view: RelativeLayout, audio: Boolean?) {
    if (cameraView?.audio != audio)
      addChangedPropToTransaction(view, "audio")
    cameraView?.audio = audio
  }

  @ReactProp(name = "enableFrameProcessor")
  fun setEnableFrameProcessor(view: RelativeLayout, enableFrameProcessor: Boolean) {
    if (cameraView?.enableFrameProcessor != enableFrameProcessor)
      addChangedPropToTransaction(view, "enableFrameProcessor")
    cameraView?.enableFrameProcessor = enableFrameProcessor
  }

  @ReactProp(name = "enableDepthData")
  fun setEnableDepthData(view: RelativeLayout, enableDepthData: Boolean) {
    if (cameraView?.enableDepthData != enableDepthData)
      addChangedPropToTransaction(view, "enableDepthData")
    cameraView?.enableDepthData = enableDepthData
  }

  @ReactProp(name = "enableHighQualityPhotos")
  fun setEnableHighQualityPhotos(view: RelativeLayout, enableHighQualityPhotos: Boolean?) {
    if (cameraView?.enableHighQualityPhotos != enableHighQualityPhotos)
      addChangedPropToTransaction(view, "enableHighQualityPhotos")
    cameraView?.enableHighQualityPhotos = enableHighQualityPhotos
  }

  @ReactProp(name = "enablePortraitEffectsMatteDelivery")
  fun setEnablePortraitEffectsMatteDelivery(view: RelativeLayout, enablePortraitEffectsMatteDelivery: Boolean) {
    if (cameraView?.enablePortraitEffectsMatteDelivery != enablePortraitEffectsMatteDelivery)
      addChangedPropToTransaction(view, "enablePortraitEffectsMatteDelivery")
    cameraView?.enablePortraitEffectsMatteDelivery = enablePortraitEffectsMatteDelivery
  }

  @ReactProp(name = "format")
  fun setFormat(view: RelativeLayout, format: ReadableMap?) {
    if (cameraView?.format != format)
      addChangedPropToTransaction(view, "format")
    cameraView?.format = format
  }

  // TODO: Change when TurboModules release.
  // We're treating -1 as "null" here, because when I make the fps parameter
  // of type "Int?" the react bridge throws an error.
  @ReactProp(name = "fps", defaultInt = -1)
  fun setFps(view: RelativeLayout, fps: Int) {
    if (cameraView?.fps != fps)
      addChangedPropToTransaction(view, "fps")
    cameraView?.fps = if (fps > 0) fps else null
  }

  @ReactProp(name = "frameProcessorFps", defaultDouble = 1.0)
  fun setFrameProcessorFps(view: RelativeLayout, frameProcessorFps: Double) {
    if (cameraView?.frameProcessorFps != frameProcessorFps)
      addChangedPropToTransaction(view, "frameProcessorFps")
    cameraView?.frameProcessorFps = frameProcessorFps
  }

  @ReactProp(name = "hdr")
  fun setHdr(view: RelativeLayout, hdr: Boolean?) {
    if (cameraView?.hdr != hdr)
      addChangedPropToTransaction(view, "hdr")
    cameraView?.hdr = hdr
  }

  @ReactProp(name = "lowLightBoost")
  fun setLowLightBoost(view: RelativeLayout, lowLightBoost: Boolean?) {
    if (cameraView?.lowLightBoost != lowLightBoost)
      addChangedPropToTransaction(view, "lowLightBoost")
    cameraView?.lowLightBoost = lowLightBoost
  }

  @ReactProp(name = "colorSpace")
  fun setColorSpace(view: RelativeLayout, colorSpace: String?) {
    if (cameraView?.colorSpace != colorSpace)
      addChangedPropToTransaction(view, "colorSpace")
    cameraView?.colorSpace = colorSpace
  }

  @ReactProp(name = "isActive")
  fun setIsActive(view: RelativeLayout, isActive: Boolean) {
    if (cameraView?.isActive != isActive)
      addChangedPropToTransaction(view, "isActive")
    cameraView?.isActive = isActive
  }

  @ReactProp(name = "torch")
  fun setTorch(view: RelativeLayout, torch: String) {
    if (cameraView?.torch != torch)
      addChangedPropToTransaction(view, "torch")
    cameraView?.torch = torch
  }

  @ReactProp(name = "zoom")
  fun setZoom(view: RelativeLayout, zoom: Double) {
    val zoomFloat = zoom.toFloat()
    if (cameraView?.zoom != zoomFloat)
      addChangedPropToTransaction(view, "zoom")
    cameraView?.zoom = zoomFloat
  }

  @ReactProp(name = "enableZoomGesture")
  fun setEnableZoomGesture(view: RelativeLayout, enableZoomGesture: Boolean) {
    if (cameraView?.enableZoomGesture != enableZoomGesture)
      addChangedPropToTransaction(view, "enableZoomGesture")
    cameraView?.enableZoomGesture = enableZoomGesture
  }

  @ReactProp(name = "orientation")
  fun setOrientation(view: RelativeLayout, orientation: String) {
    if (cameraView?.orientation != orientation)
      addChangedPropToTransaction(view, "orientation")
    cameraView?.orientation = orientation
  }

  companion object {
    const val TAG = "CameraView"

    val cameraViewTransactions: HashMap<RelativeLayout, ArrayList<String>> = HashMap()

    private fun addChangedPropToTransaction(view: RelativeLayout, changedProp: String) {
      if (cameraViewTransactions[view] == null) {
        cameraViewTransactions[view] = ArrayList()
      }
      cameraViewTransactions[view]!!.add(changedProp)
    }
  }
}
