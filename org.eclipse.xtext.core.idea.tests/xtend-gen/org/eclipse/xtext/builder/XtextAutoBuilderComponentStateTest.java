package org.eclipse.xtext.builder;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.build.Source2GeneratedMapping;
import org.eclipse.xtext.idea.build.XtextAutoBuilderComponentState;
import org.eclipse.xtext.index.IndexTestLanguageInjectorProvider;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.ChunkedResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;
import org.eclipse.xtext.resource.impl.ResourceServiceProviderRegistryImpl;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(IndexTestLanguageInjectorProvider.class)
@SuppressWarnings("all")
public class XtextAutoBuilderComponentStateTest {
  @Extension
  private final XtextAutoBuilderComponentState.Codec _codec = new XtextAutoBuilderComponentState.Codec();
  
  @Inject
  @Extension
  private ParseHelper<?> _parseHelper;
  
  @Inject
  @Extension
  private IResourceDescription.Manager _manager;
  
  @Test
  public void testSource2GeneratedMap() {
    final Source2GeneratedMapping s2g0 = new Source2GeneratedMapping();
    s2g0.addSource2Generated(URI.createURI("foo"), URI.createURI("foo_"));
    s2g0.addSource2Generated(URI.createURI("foo"), URI.createURI("baz_"));
    s2g0.addSource2Generated(URI.createURI("bar"), URI.createURI("bar_"));
    s2g0.addSource2Generated(URI.createURI("bar"), URI.createURI("baz_"));
    final Source2GeneratedMapping s2g1 = new Source2GeneratedMapping();
    s2g1.addSource2Generated(URI.createURI("foobar"), URI.createURI("foobar_"));
    s2g1.addSource2Generated(URI.createURI("foobar"), URI.createURI("foobar2_"));
    Pair<String, Source2GeneratedMapping> _mappedTo = Pair.<String, Source2GeneratedMapping>of("module0", s2g0);
    Pair<String, Source2GeneratedMapping> _mappedTo_1 = Pair.<String, Source2GeneratedMapping>of("module1", s2g1);
    final Map<String, Source2GeneratedMapping> map = Collections.<String, Source2GeneratedMapping>unmodifiableMap(CollectionLiterals.<String, Source2GeneratedMapping>newHashMap(_mappedTo, _mappedTo_1));
    ResourceServiceProviderRegistryImpl _resourceServiceProviderRegistryImpl = new ResourceServiceProviderRegistryImpl();
    ChunkedResourceDescriptions _chunkedResourceDescriptions = new ChunkedResourceDescriptions();
    final XtextAutoBuilderComponentState state = this._codec.encode(_resourceServiceProviderRegistryImpl, _chunkedResourceDescriptions, map);
    final Map<String, Source2GeneratedMapping> decodedState = this._codec.decodeModuleToGenerated(state);
    Assert.assertEquals(2, decodedState.keySet().size());
    final Source2GeneratedMapping _s2g0 = decodedState.get("module0");
    Assert.assertNotNull(_s2g0);
    URI _createURI = URI.createURI("foo_");
    URI _createURI_1 = URI.createURI("bar_");
    URI _createURI_2 = URI.createURI("baz_");
    Assert.assertEquals(Collections.<URI>unmodifiableSet(CollectionLiterals.<URI>newHashSet(_createURI, _createURI_1, _createURI_2)), IterableExtensions.<URI>toSet(_s2g0.getAllGenerated()));
    URI _createURI_3 = URI.createURI("foo_");
    URI _createURI_4 = URI.createURI("baz_");
    Assert.assertEquals(Collections.<URI>unmodifiableSet(CollectionLiterals.<URI>newHashSet(_createURI_3, _createURI_4)), IterableExtensions.<URI>toSet(_s2g0.getGenerated(URI.createURI("foo"))));
    URI _createURI_5 = URI.createURI("bar_");
    URI _createURI_6 = URI.createURI("baz_");
    Assert.assertEquals(Collections.<URI>unmodifiableSet(CollectionLiterals.<URI>newHashSet(_createURI_5, _createURI_6)), IterableExtensions.<URI>toSet(_s2g0.getGenerated(URI.createURI("bar"))));
    URI _createURI_7 = URI.createURI("foo");
    Assert.assertEquals(Collections.<URI>unmodifiableSet(CollectionLiterals.<URI>newHashSet(_createURI_7)), IterableExtensions.<URI>toSet(_s2g0.getSource(URI.createURI("foo_"))));
    URI _createURI_8 = URI.createURI("bar");
    Assert.assertEquals(Collections.<URI>unmodifiableSet(CollectionLiterals.<URI>newHashSet(_createURI_8)), IterableExtensions.<URI>toSet(_s2g0.getSource(URI.createURI("bar_"))));
    URI _createURI_9 = URI.createURI("foo");
    URI _createURI_10 = URI.createURI("bar");
    Assert.assertEquals(Collections.<URI>unmodifiableSet(CollectionLiterals.<URI>newHashSet(_createURI_9, _createURI_10)), IterableExtensions.<URI>toSet(_s2g0.getSource(URI.createURI("baz_"))));
    final Source2GeneratedMapping _s2g1 = decodedState.get("module1");
    Assert.assertNotNull(_s2g1);
    URI _createURI_11 = URI.createURI("foobar_");
    URI _createURI_12 = URI.createURI("foobar2_");
    Assert.assertEquals(Collections.<URI>unmodifiableSet(CollectionLiterals.<URI>newHashSet(_createURI_11, _createURI_12)), IterableExtensions.<URI>toSet(_s2g1.getAllGenerated()));
    URI _createURI_13 = URI.createURI("foobar_");
    URI _createURI_14 = URI.createURI("foobar2_");
    Assert.assertEquals(Collections.<URI>unmodifiableSet(CollectionLiterals.<URI>newHashSet(_createURI_13, _createURI_14)), IterableExtensions.<URI>toSet(_s2g1.getGenerated(URI.createURI("foobar"))));
    URI _createURI_15 = URI.createURI("foobar");
    Assert.assertEquals(Collections.<URI>unmodifiableSet(CollectionLiterals.<URI>newHashSet(_createURI_15)), IterableExtensions.<URI>toSet(_s2g1.getSource(URI.createURI("foobar_"))));
    URI _createURI_16 = URI.createURI("foobar");
    Assert.assertEquals(Collections.<URI>unmodifiableSet(CollectionLiterals.<URI>newHashSet(_createURI_16)), IterableExtensions.<URI>toSet(_s2g1.getSource(URI.createURI("foobar2_"))));
  }
  
  @Test
  public void testIndex() {
    try {
      final HashMap<String, ResourceDescriptionsData> map = CollectionLiterals.<String, ResourceDescriptionsData>newHashMap();
      IntegerRange _upTo = new IntegerRange(0, 1);
      for (final Integer j : _upTo) {
        {
          final XtextResourceSet rs = new XtextResourceSet();
          final ArrayList<IResourceDescription> descriptions = CollectionLiterals.<IResourceDescription>newArrayList();
          IntegerRange _upTo_1 = new IntegerRange(0, 1);
          for (final Integer i : _upTo_1) {
            {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("entity Foo1{}");
              _builder.newLine();
              final Resource r = this._parseHelper.parse(_builder, URI.createURI((("Foo" + i) + ".indextestlanguage")), rs).eResource();
              IResourceDescription _resourceDescription = this._manager.getResourceDescription(r);
              descriptions.add(_resourceDescription);
            }
          }
          ResourceDescriptionsData _resourceDescriptionsData = new ResourceDescriptionsData(descriptions);
          map.put(("module" + j), _resourceDescriptionsData);
        }
      }
      final ChunkedResourceDescriptions index = new ChunkedResourceDescriptions(map);
      ResourceServiceProviderRegistryImpl _resourceServiceProviderRegistryImpl = new ResourceServiceProviderRegistryImpl();
      final XtextAutoBuilderComponentState state = this._codec.encode(_resourceServiceProviderRegistryImpl, index, CollectionLiterals.<String, Source2GeneratedMapping>emptyMap());
      final ChunkedResourceDescriptions _index = this._codec.decodeIndex(state);
      Assert.assertEquals(IterableExtensions.size(index.getAllResourceDescriptions()), IterableExtensions.size(_index.getAllResourceDescriptions()));
      Iterable<IResourceDescription> _allResourceDescriptions = index.getAllResourceDescriptions();
      for (final IResourceDescription desc : _allResourceDescriptions) {
        Assert.assertFalse(this._manager.createDelta(desc, _index.getResourceDescription(desc.getURI())).haveEObjectDescriptionsChanged());
      }
      final Iterable<IResourceDescription> _module0 = _index.getContainer("module1").getAllResourceDescriptions();
      final Function1<IResourceDescription, String> _function = (IResourceDescription it) -> {
        return it.getURI().lastSegment().toString();
      };
      Assert.assertEquals(Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("Foo0.indextestlanguage", "Foo1.indextestlanguage")), IterableExtensions.<String>toSet(IterableExtensions.<IResourceDescription, String>map(_module0, _function)));
      final Iterable<IResourceDescription> _module1 = _index.getContainer("module1").getAllResourceDescriptions();
      final Function1<IResourceDescription, String> _function_1 = (IResourceDescription it) -> {
        return it.getURI().lastSegment().toString();
      };
      Assert.assertEquals(Collections.<String>unmodifiableSet(CollectionLiterals.<String>newHashSet("Foo0.indextestlanguage", "Foo1.indextestlanguage")), IterableExtensions.<String>toSet(IterableExtensions.<IResourceDescription, String>map(_module1, _function_1)));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
