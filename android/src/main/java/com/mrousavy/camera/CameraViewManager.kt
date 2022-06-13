package com.mrousavy.camera

import android.view.View
import android.widget.RelativeLayout
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.views.view.ReactViewGroup

@Suppress("unused")
class CameraViewManager(reactContext: ReactApplicationContext) : ViewGroupManager<RelativeLayout>() {
  public override fun createViewInstance(context: ThemedReactContext): RelativeLayout {
    val viewGroup = RelativeLayout(context)

    createNewViewLayout(context, viewGroup)

    return viewGroup
  }

  override fun addView(parent: RelativeLayout?, child: View?, index: Int) {
    val reactViewGroup = reactViewGroups[parent]
    reactViewGroup?.addView(child, index)
  }

  override fun addViews(parent: RelativeLayout?, views: MutableList<View>?) {
    views?.forEach { addView(parent, it, 0) }
  }

  override fun onAfterUpdateTransaction(view: RelativeLayout) {
    super.onAfterUpdateTransaction(view)

    val changedProps = cameraViewTransactions[view] ?: ArrayList()
    val cameraView = cameraViews[view]
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
    val cameraView = cameraViews[view]
    if (cameraView?.cameraId != cameraId)
      addChangedPropToTransaction(view, "cameraId")
    cameraView?.cameraId = cameraId
  }

  @ReactProp(name = "photo")
  fun setPhoto(view: RelativeLayout, photo: Boolean?) {
    val cameraView = cameraViews[view]
    if (cameraView?.photo != photo)
      addChangedPropToTransaction(view, "photo")
    cameraView?.photo = photo
  }

  @ReactProp(name = "video")
  fun setVideo(view: RelativeLayout, video: Boolean?) {
    val cameraView = cameraViews[view]
    if (cameraView?.video != video)
      addChangedPropToTransaction(view, "video")
    cameraView?.video = video
  }

  @ReactProp(name = "audio")
  fun setAudio(view: RelativeLayout, audio: Boolean?) {
    val cameraView = cameraViews[view]
    if (cameraView?.audio != audio)
      addChangedPropToTransaction(view, "audio")
    cameraView?.audio = audio
  }

  @ReactProp(name = "enableFrameProcessor")
  fun setEnableFrameProcessor(view: RelativeLayout, enableFrameProcessor: Boolean) {
    val cameraView = cameraViews[view]
    if (cameraView?.enableFrameProcessor != enableFrameProcessor)
      addChangedPropToTransaction(view, "enableFrameProcessor")
    cameraView?.enableFrameProcessor = enableFrameProcessor
  }

  @ReactProp(name = "enableDepthData")
  fun setEnableDepthData(view: RelativeLayout, enableDepthData: Boolean) {
    val cameraView = cameraViews[view]
    if (cameraView?.enableDepthData != enableDepthData)
      addChangedPropToTransaction(view, "enableDepthData")
    cameraView?.enableDepthData = enableDepthData
  }

  @ReactProp(name = "enableHighQualityPhotos")
  fun setEnableHighQualityPhotos(view: RelativeLayout, enableHighQualityPhotos: Boolean?) {
    val cameraView = cameraViews[view]
    if (cameraView?.enableHighQualityPhotos != enableHighQualityPhotos)
      addChangedPropToTransaction(view, "enableHighQualityPhotos")
    cameraView?.enableHighQualityPhotos = enableHighQualityPhotos
  }

  @ReactProp(name = "enablePortraitEffectsMatteDelivery")
  fun setEnablePortraitEffectsMatteDelivery(view: RelativeLayout, enablePortraitEffectsMatteDelivery: Boolean) {
    val cameraView = cameraViews[view]
    if (cameraView?.enablePortraitEffectsMatteDelivery != enablePortraitEffectsMatteDelivery)
      addChangedPropToTransaction(view, "enablePortraitEffectsMatteDelivery")
    cameraView?.enablePortraitEffectsMatteDelivery = enablePortraitEffectsMatteDelivery
  }

  @ReactProp(name = "format")
  fun setFormat(view: RelativeLayout, format: ReadableMap?) {
    val cameraView = cameraViews[view]
    if (cameraView?.format != format)
      addChangedPropToTransaction(view, "format")
    cameraView?.format = format
  }

  // TODO: Change when TurboModules release.
  // We're treating -1 as "null" here, because when I make the fps parameter
  // of type "Int?" the react bridge throws an error.
  @ReactProp(name = "fps", defaultInt = -1)
  fun setFps(view: RelativeLayout, fps: Int) {
    val cameraView = cameraViews[view]
    if (cameraView?.fps != fps)
      addChangedPropToTransaction(view, "fps")
    cameraView?.fps = if (fps > 0) fps else null
  }

  @ReactProp(name = "frameProcessorFps", defaultDouble = 1.0)
  fun setFrameProcessorFps(view: RelativeLayout, frameProcessorFps: Double) {
    val cameraView = cameraViews[view]
    if (cameraView?.frameProcessorFps != frameProcessorFps)
      addChangedPropToTransaction(view, "frameProcessorFps")
    cameraView?.frameProcessorFps = frameProcessorFps
  }

  @ReactProp(name = "hdr")
  fun setHdr(view: RelativeLayout, hdr: Boolean?) {
    val cameraView = cameraViews[view]
    if (cameraView?.hdr != hdr)
      addChangedPropToTransaction(view, "hdr")
    cameraView?.hdr = hdr
  }

  @ReactProp(name = "lowLightBoost")
  fun setLowLightBoost(view: RelativeLayout, lowLightBoost: Boolean?) {
    val cameraView = cameraViews[view]
    if (cameraView?.lowLightBoost != lowLightBoost)
      addChangedPropToTransaction(view, "lowLightBoost")
    cameraView?.lowLightBoost = lowLightBoost
  }

  @ReactProp(name = "colorSpace")
  fun setColorSpace(view: RelativeLayout, colorSpace: String?) {
    val cameraView = cameraViews[view]
    if (cameraView?.colorSpace != colorSpace)
      addChangedPropToTransaction(view, "colorSpace")
    cameraView?.colorSpace = colorSpace
  }

  @ReactProp(name = "isActive")
  fun setIsActive(view: RelativeLayout, isActive: Boolean) {
    val cameraView = cameraViews[view]
    if (cameraView?.isActive != isActive)
      addChangedPropToTransaction(view, "isActive")
    cameraView?.isActive = isActive
  }

  @ReactProp(name = "torch")
  fun setTorch(view: RelativeLayout, torch: String) {
    val cameraView = cameraViews[view]
    if (cameraView?.torch != torch)
      addChangedPropToTransaction(view, "torch")
    cameraView?.torch = torch
  }

  @ReactProp(name = "zoom")
  fun setZoom(view: RelativeLayout, zoom: Double) {
    val cameraView = cameraViews[view]
    val zoomFloat = zoom.toFloat()
    if (cameraView?.zoom != zoomFloat)
      addChangedPropToTransaction(view, "zoom")
    cameraView?.zoom = zoomFloat
  }

  @ReactProp(name = "enableZoomGesture")
  fun setEnableZoomGesture(view: RelativeLayout, enableZoomGesture: Boolean) {
    val cameraView = cameraViews[view]
    if (cameraView?.enableZoomGesture != enableZoomGesture)
      addChangedPropToTransaction(view, "enableZoomGesture")
    cameraView?.enableZoomGesture = enableZoomGesture
  }

  @ReactProp(name = "orientation")
  fun setOrientation(view: RelativeLayout, orientation: String) {
    val cameraView = cameraViews[view]
    if (cameraView?.orientation != orientation)
      addChangedPropToTransaction(view, "orientation")
    cameraView?.orientation = orientation
  }

  companion object {
    const val TAG = "CameraView"

    val cameraViewTransactions: HashMap<RelativeLayout, ArrayList<String>> = HashMap()
    val cameraViews: HashMap<RelativeLayout, CameraView> = HashMap()
    val reactViewGroups: HashMap<RelativeLayout, ReactViewGroup> = HashMap()

    private fun addChangedPropToTransaction(view: RelativeLayout, changedProp: String) {
      if (cameraViewTransactions[view] == null) {
        cameraViewTransactions[view] = ArrayList()
      }
      cameraViewTransactions[view]!!.add(changedProp)
    }

    private fun createNewViewLayout(context: ReactContext, viewGroup: RelativeLayout) {
      val layoutParams = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.MATCH_PARENT,
        RelativeLayout.LayoutParams.MATCH_PARENT
      )

      val cameraView = CameraView(context, context.getNativeModule(CameraViewModule::class.java)!!.frameProcessorThread)
      cameraView.layoutParams = layoutParams

      val reactViewGroup = ReactViewGroup(context)
      reactViewGroup.layoutParams = layoutParams

      viewGroup.addView(cameraView)
      viewGroup.addView(reactViewGroup)

      cameraViews[viewGroup] = cameraView;
      reactViewGroups[viewGroup] = reactViewGroup
    }
  }
}
